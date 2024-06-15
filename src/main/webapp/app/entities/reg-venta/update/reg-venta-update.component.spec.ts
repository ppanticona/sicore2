import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { RegVentaFormService } from './reg-venta-form.service';
import { RegVentaService } from '../service/reg-venta.service';
import { IRegVenta } from '../reg-venta.model';
import { IOrden } from 'app/entities/orden/orden.model';
import { OrdenService } from 'app/entities/orden/service/orden.service';

import { RegVentaUpdateComponent } from './reg-venta-update.component';

describe('RegVenta Management Update Component', () => {
  let comp: RegVentaUpdateComponent;
  let fixture: ComponentFixture<RegVentaUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let regVentaFormService: RegVentaFormService;
  let regVentaService: RegVentaService;
  let ordenService: OrdenService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [RegVentaUpdateComponent],
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
      .overrideTemplate(RegVentaUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(RegVentaUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    regVentaFormService = TestBed.inject(RegVentaFormService);
    regVentaService = TestBed.inject(RegVentaService);
    ordenService = TestBed.inject(OrdenService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Orden query and add missing value', () => {
      const regVenta: IRegVenta = { id: 'CBA' };
      const orden: IOrden = { id: 'f44f73d0-5a23-46f5-ba14-7bb40a7d7031' };
      regVenta.orden = orden;

      const ordenCollection: IOrden[] = [{ id: 'a55a6fae-c076-43b9-80ee-8258c2bb5067' }];
      jest.spyOn(ordenService, 'query').mockReturnValue(of(new HttpResponse({ body: ordenCollection })));
      const additionalOrdens = [orden];
      const expectedCollection: IOrden[] = [...additionalOrdens, ...ordenCollection];
      jest.spyOn(ordenService, 'addOrdenToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ regVenta });
      comp.ngOnInit();

      expect(ordenService.query).toHaveBeenCalled();
      expect(ordenService.addOrdenToCollectionIfMissing).toHaveBeenCalledWith(
        ordenCollection,
        ...additionalOrdens.map(expect.objectContaining)
      );
      expect(comp.ordensSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const regVenta: IRegVenta = { id: 'CBA' };
      const orden: IOrden = { id: '6deaa0ea-67e4-47a5-bc57-7f9a87075eed' };
      regVenta.orden = orden;

      activatedRoute.data = of({ regVenta });
      comp.ngOnInit();

      expect(comp.ordensSharedCollection).toContain(orden);
      expect(comp.regVenta).toEqual(regVenta);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRegVenta>>();
      const regVenta = { id: 'ABC' };
      jest.spyOn(regVentaFormService, 'getRegVenta').mockReturnValue(regVenta);
      jest.spyOn(regVentaService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ regVenta });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: regVenta }));
      saveSubject.complete();

      // THEN
      expect(regVentaFormService.getRegVenta).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(regVentaService.update).toHaveBeenCalledWith(expect.objectContaining(regVenta));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRegVenta>>();
      const regVenta = { id: 'ABC' };
      jest.spyOn(regVentaFormService, 'getRegVenta').mockReturnValue({ id: null });
      jest.spyOn(regVentaService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ regVenta: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: regVenta }));
      saveSubject.complete();

      // THEN
      expect(regVentaFormService.getRegVenta).toHaveBeenCalled();
      expect(regVentaService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRegVenta>>();
      const regVenta = { id: 'ABC' };
      jest.spyOn(regVentaService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ regVenta });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(regVentaService.update).toHaveBeenCalled();
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
