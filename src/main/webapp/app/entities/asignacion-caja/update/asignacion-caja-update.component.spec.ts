import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { AsignacionCajaFormService } from './asignacion-caja-form.service';
import { AsignacionCajaService } from '../service/asignacion-caja.service';
import { IAsignacionCaja } from '../asignacion-caja.model';
import { IEmpleados } from 'app/entities/empleados/empleados.model';
import { EmpleadosService } from 'app/entities/empleados/service/empleados.service';
import { ICaja } from 'app/entities/caja/caja.model';
import { CajaService } from 'app/entities/caja/service/caja.service';

import { AsignacionCajaUpdateComponent } from './asignacion-caja-update.component';

describe('AsignacionCaja Management Update Component', () => {
  let comp: AsignacionCajaUpdateComponent;
  let fixture: ComponentFixture<AsignacionCajaUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let asignacionCajaFormService: AsignacionCajaFormService;
  let asignacionCajaService: AsignacionCajaService;
  let empleadosService: EmpleadosService;
  let cajaService: CajaService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [AsignacionCajaUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(AsignacionCajaUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(AsignacionCajaUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    asignacionCajaFormService = TestBed.inject(AsignacionCajaFormService);
    asignacionCajaService = TestBed.inject(AsignacionCajaService);
    empleadosService = TestBed.inject(EmpleadosService);
    cajaService = TestBed.inject(CajaService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Empleados query and add missing value', () => {
      const asignacionCaja: IAsignacionCaja = { id: 'CBA' };
      const userId: IEmpleados = { id: 'fc38b054-8d4f-4051-9efa-c15db07df025' };
      asignacionCaja.userId = userId;

      const empleadosCollection: IEmpleados[] = [{ id: '27265742-3c64-4777-a5ad-335110f3cade' }];
      jest.spyOn(empleadosService, 'query').mockReturnValue(of(new HttpResponse({ body: empleadosCollection })));
      const additionalEmpleados = [userId];
      const expectedCollection: IEmpleados[] = [...additionalEmpleados, ...empleadosCollection];
      jest.spyOn(empleadosService, 'addEmpleadosToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ asignacionCaja });
      comp.ngOnInit();

      expect(empleadosService.query).toHaveBeenCalled();
      expect(empleadosService.addEmpleadosToCollectionIfMissing).toHaveBeenCalledWith(
        empleadosCollection,
        ...additionalEmpleados.map(expect.objectContaining)
      );
      expect(comp.empleadosSharedCollection).toEqual(expectedCollection);
    });

    it('Should call Caja query and add missing value', () => {
      const asignacionCaja: IAsignacionCaja = { id: 'CBA' };
      const caja: ICaja = { id: '628c46fa-3ad3-4147-8708-bcf3613eaaac' };
      asignacionCaja.caja = caja;

      const cajaCollection: ICaja[] = [{ id: '8e416d37-4b89-487b-a17f-b7e1c1fbc223' }];
      jest.spyOn(cajaService, 'query').mockReturnValue(of(new HttpResponse({ body: cajaCollection })));
      const additionalCajas = [caja];
      const expectedCollection: ICaja[] = [...additionalCajas, ...cajaCollection];
      jest.spyOn(cajaService, 'addCajaToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ asignacionCaja });
      comp.ngOnInit();

      expect(cajaService.query).toHaveBeenCalled();
      expect(cajaService.addCajaToCollectionIfMissing).toHaveBeenCalledWith(
        cajaCollection,
        ...additionalCajas.map(expect.objectContaining)
      );
      expect(comp.cajasSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const asignacionCaja: IAsignacionCaja = { id: 'CBA' };
      const userId: IEmpleados = { id: '1b265ce2-96cb-4e0e-b946-624e94e04073' };
      asignacionCaja.userId = userId;
      const caja: ICaja = { id: 'd9925a29-1995-4fe2-90ff-23ae38f39020' };
      asignacionCaja.caja = caja;

      activatedRoute.data = of({ asignacionCaja });
      comp.ngOnInit();

      expect(comp.empleadosSharedCollection).toContain(userId);
      expect(comp.cajasSharedCollection).toContain(caja);
      expect(comp.asignacionCaja).toEqual(asignacionCaja);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAsignacionCaja>>();
      const asignacionCaja = { id: 'ABC' };
      jest.spyOn(asignacionCajaFormService, 'getAsignacionCaja').mockReturnValue(asignacionCaja);
      jest.spyOn(asignacionCajaService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ asignacionCaja });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: asignacionCaja }));
      saveSubject.complete();

      // THEN
      expect(asignacionCajaFormService.getAsignacionCaja).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(asignacionCajaService.update).toHaveBeenCalledWith(expect.objectContaining(asignacionCaja));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAsignacionCaja>>();
      const asignacionCaja = { id: 'ABC' };
      jest.spyOn(asignacionCajaFormService, 'getAsignacionCaja').mockReturnValue({ id: null });
      jest.spyOn(asignacionCajaService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ asignacionCaja: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: asignacionCaja }));
      saveSubject.complete();

      // THEN
      expect(asignacionCajaFormService.getAsignacionCaja).toHaveBeenCalled();
      expect(asignacionCajaService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAsignacionCaja>>();
      const asignacionCaja = { id: 'ABC' };
      jest.spyOn(asignacionCajaService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ asignacionCaja });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(asignacionCajaService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareEmpleados', () => {
      it('Should forward to empleadosService', () => {
        const entity = { id: 'ABC' };
        const entity2 = { id: 'CBA' };
        jest.spyOn(empleadosService, 'compareEmpleados');
        comp.compareEmpleados(entity, entity2);
        expect(empleadosService.compareEmpleados).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareCaja', () => {
      it('Should forward to cajaService', () => {
        const entity = { id: 'ABC' };
        const entity2 = { id: 'CBA' };
        jest.spyOn(cajaService, 'compareCaja');
        comp.compareCaja(entity, entity2);
        expect(cajaService.compareCaja).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
