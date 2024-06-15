import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { PrecuentaFormService } from './precuenta-form.service';
import { PrecuentaService } from '../service/precuenta.service';
import { IPrecuenta } from '../precuenta.model';
import { IOrden } from 'app/entities/orden/orden.model';
import { OrdenService } from 'app/entities/orden/service/orden.service';

import { PrecuentaUpdateComponent } from './precuenta-update.component';

describe('Precuenta Management Update Component', () => {
  let comp: PrecuentaUpdateComponent;
  let fixture: ComponentFixture<PrecuentaUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let precuentaFormService: PrecuentaFormService;
  let precuentaService: PrecuentaService;
  let ordenService: OrdenService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [PrecuentaUpdateComponent],
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
      .overrideTemplate(PrecuentaUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(PrecuentaUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    precuentaFormService = TestBed.inject(PrecuentaFormService);
    precuentaService = TestBed.inject(PrecuentaService);
    ordenService = TestBed.inject(OrdenService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Orden query and add missing value', () => {
      const precuenta: IPrecuenta = { id: 'CBA' };
      const orden: IOrden = { id: '0a812f4a-062e-4abb-9231-73287dcc3fc5' };
      precuenta.orden = orden;

      const ordenCollection: IOrden[] = [{ id: '6c216ba6-34e3-448a-a8f5-1acbe9978d48' }];
      jest.spyOn(ordenService, 'query').mockReturnValue(of(new HttpResponse({ body: ordenCollection })));
      const additionalOrdens = [orden];
      const expectedCollection: IOrden[] = [...additionalOrdens, ...ordenCollection];
      jest.spyOn(ordenService, 'addOrdenToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ precuenta });
      comp.ngOnInit();

      expect(ordenService.query).toHaveBeenCalled();
      expect(ordenService.addOrdenToCollectionIfMissing).toHaveBeenCalledWith(
        ordenCollection,
        ...additionalOrdens.map(expect.objectContaining)
      );
      expect(comp.ordensSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const precuenta: IPrecuenta = { id: 'CBA' };
      const orden: IOrden = { id: 'db16a118-db2b-4824-94a5-46c6fc3e4486' };
      precuenta.orden = orden;

      activatedRoute.data = of({ precuenta });
      comp.ngOnInit();

      expect(comp.ordensSharedCollection).toContain(orden);
      expect(comp.precuenta).toEqual(precuenta);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IPrecuenta>>();
      const precuenta = { id: 'ABC' };
      jest.spyOn(precuentaFormService, 'getPrecuenta').mockReturnValue(precuenta);
      jest.spyOn(precuentaService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ precuenta });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: precuenta }));
      saveSubject.complete();

      // THEN
      expect(precuentaFormService.getPrecuenta).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(precuentaService.update).toHaveBeenCalledWith(expect.objectContaining(precuenta));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IPrecuenta>>();
      const precuenta = { id: 'ABC' };
      jest.spyOn(precuentaFormService, 'getPrecuenta').mockReturnValue({ id: null });
      jest.spyOn(precuentaService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ precuenta: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: precuenta }));
      saveSubject.complete();

      // THEN
      expect(precuentaFormService.getPrecuenta).toHaveBeenCalled();
      expect(precuentaService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IPrecuenta>>();
      const precuenta = { id: 'ABC' };
      jest.spyOn(precuentaService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ precuenta });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(precuentaService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareOrden', () => {
      it('Should forward to ordenService', () => {
        const entity = { id: 'ABC' };
        const entity2 = { id: 'CBA' };
        jest.spyOn(ordenService, 'compareOrden');
        comp.compareOrden(entity, entity2);
        expect(ordenService.compareOrden).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
