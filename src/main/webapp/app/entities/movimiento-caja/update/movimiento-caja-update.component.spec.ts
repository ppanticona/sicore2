import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { MovimientoCajaFormService } from './movimiento-caja-form.service';
import { MovimientoCajaService } from '../service/movimiento-caja.service';
import { IMovimientoCaja } from '../movimiento-caja.model';
import { IAsignacionCaja } from 'app/entities/asignacion-caja/asignacion-caja.model';
import { AsignacionCajaService } from 'app/entities/asignacion-caja/service/asignacion-caja.service';
import { IAutorizacion } from 'app/entities/autorizacion/autorizacion.model';
import { AutorizacionService } from 'app/entities/autorizacion/service/autorizacion.service';

import { MovimientoCajaUpdateComponent } from './movimiento-caja-update.component';

describe('MovimientoCaja Management Update Component', () => {
  let comp: MovimientoCajaUpdateComponent;
  let fixture: ComponentFixture<MovimientoCajaUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let movimientoCajaFormService: MovimientoCajaFormService;
  let movimientoCajaService: MovimientoCajaService;
  let asignacionCajaService: AsignacionCajaService;
  let autorizacionService: AutorizacionService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [MovimientoCajaUpdateComponent],
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
      .overrideTemplate(MovimientoCajaUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(MovimientoCajaUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    movimientoCajaFormService = TestBed.inject(MovimientoCajaFormService);
    movimientoCajaService = TestBed.inject(MovimientoCajaService);
    asignacionCajaService = TestBed.inject(AsignacionCajaService);
    autorizacionService = TestBed.inject(AutorizacionService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call AsignacionCaja query and add missing value', () => {
      const movimientoCaja: IMovimientoCaja = { id: 'CBA' };
      const asignacionCaja: IAsignacionCaja = { id: 'f70d2bc2-849e-4e55-9140-dfc99d6d88f8' };
      movimientoCaja.asignacionCaja = asignacionCaja;

      const asignacionCajaCollection: IAsignacionCaja[] = [{ id: 'b573cda8-4cb8-4614-8d08-df80aed647f0' }];
      jest.spyOn(asignacionCajaService, 'query').mockReturnValue(of(new HttpResponse({ body: asignacionCajaCollection })));
      const additionalAsignacionCajas = [asignacionCaja];
      const expectedCollection: IAsignacionCaja[] = [...additionalAsignacionCajas, ...asignacionCajaCollection];
      jest.spyOn(asignacionCajaService, 'addAsignacionCajaToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ movimientoCaja });
      comp.ngOnInit();

      expect(asignacionCajaService.query).toHaveBeenCalled();
      expect(asignacionCajaService.addAsignacionCajaToCollectionIfMissing).toHaveBeenCalledWith(
        asignacionCajaCollection,
        ...additionalAsignacionCajas.map(expect.objectContaining)
      );
      expect(comp.asignacionCajasSharedCollection).toEqual(expectedCollection);
    });

    it('Should call Autorizacion query and add missing value', () => {
      const movimientoCaja: IMovimientoCaja = { id: 'CBA' };
      const autorizacion: IAutorizacion = { id: 'aa8eba59-0c6a-49db-ac49-676611ed9fab' };
      movimientoCaja.autorizacion = autorizacion;

      const autorizacionCollection: IAutorizacion[] = [{ id: 'c1519af9-7917-4732-bab0-d118cfa03a5c' }];
      jest.spyOn(autorizacionService, 'query').mockReturnValue(of(new HttpResponse({ body: autorizacionCollection })));
      const additionalAutorizacions = [autorizacion];
      const expectedCollection: IAutorizacion[] = [...additionalAutorizacions, ...autorizacionCollection];
      jest.spyOn(autorizacionService, 'addAutorizacionToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ movimientoCaja });
      comp.ngOnInit();

      expect(autorizacionService.query).toHaveBeenCalled();
      expect(autorizacionService.addAutorizacionToCollectionIfMissing).toHaveBeenCalledWith(
        autorizacionCollection,
        ...additionalAutorizacions.map(expect.objectContaining)
      );
      expect(comp.autorizacionsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const movimientoCaja: IMovimientoCaja = { id: 'CBA' };
      const asignacionCaja: IAsignacionCaja = { id: 'e24dd8e2-db78-4286-8761-f744fe647ac5' };
      movimientoCaja.asignacionCaja = asignacionCaja;
      const autorizacion: IAutorizacion = { id: '1f8cff7b-eb6e-4304-8b9a-efa896272868' };
      movimientoCaja.autorizacion = autorizacion;

      activatedRoute.data = of({ movimientoCaja });
      comp.ngOnInit();

      expect(comp.asignacionCajasSharedCollection).toContain(asignacionCaja);
      expect(comp.autorizacionsSharedCollection).toContain(autorizacion);
      expect(comp.movimientoCaja).toEqual(movimientoCaja);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IMovimientoCaja>>();
      const movimientoCaja = { id: 'ABC' };
      jest.spyOn(movimientoCajaFormService, 'getMovimientoCaja').mockReturnValue(movimientoCaja);
      jest.spyOn(movimientoCajaService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ movimientoCaja });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: movimientoCaja }));
      saveSubject.complete();

      // THEN
      expect(movimientoCajaFormService.getMovimientoCaja).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(movimientoCajaService.update).toHaveBeenCalledWith(expect.objectContaining(movimientoCaja));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IMovimientoCaja>>();
      const movimientoCaja = { id: 'ABC' };
      jest.spyOn(movimientoCajaFormService, 'getMovimientoCaja').mockReturnValue({ id: null });
      jest.spyOn(movimientoCajaService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ movimientoCaja: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: movimientoCaja }));
      saveSubject.complete();

      // THEN
      expect(movimientoCajaFormService.getMovimientoCaja).toHaveBeenCalled();
      expect(movimientoCajaService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IMovimientoCaja>>();
      const movimientoCaja = { id: 'ABC' };
      jest.spyOn(movimientoCajaService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ movimientoCaja });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(movimientoCajaService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareAsignacionCaja', () => {
      it('Should forward to asignacionCajaService', () => {
        const entity = { id: 'ABC' };
        const entity2 = { id: 'CBA' };
        jest.spyOn(asignacionCajaService, 'compareAsignacionCaja');
        comp.compareAsignacionCaja(entity, entity2);
        expect(asignacionCajaService.compareAsignacionCaja).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareAutorizacion', () => {
      it('Should forward to autorizacionService', () => {
        const entity = { id: 'ABC' };
        const entity2 = { id: 'CBA' };
        jest.spyOn(autorizacionService, 'compareAutorizacion');
        comp.compareAutorizacion(entity, entity2);
        expect(autorizacionService.compareAutorizacion).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
