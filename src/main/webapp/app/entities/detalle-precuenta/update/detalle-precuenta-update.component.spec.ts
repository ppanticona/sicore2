import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { DetallePrecuentaFormService } from './detalle-precuenta-form.service';
import { DetallePrecuentaService } from '../service/detalle-precuenta.service';
import { IDetallePrecuenta } from '../detalle-precuenta.model';
import { IOrden } from 'app/entities/orden/orden.model';
import { OrdenService } from 'app/entities/orden/service/orden.service';
import { IAutorizacion } from 'app/entities/autorizacion/autorizacion.model';
import { AutorizacionService } from 'app/entities/autorizacion/service/autorizacion.service';
import { IRegVenta } from 'app/entities/reg-venta/reg-venta.model';
import { RegVentaService } from 'app/entities/reg-venta/service/reg-venta.service';
import { IPrecuenta } from 'app/entities/precuenta/precuenta.model';
import { PrecuentaService } from 'app/entities/precuenta/service/precuenta.service';
import { IDetalleOrden } from 'app/entities/detalle-orden/detalle-orden.model';
import { DetalleOrdenService } from 'app/entities/detalle-orden/service/detalle-orden.service';

import { DetallePrecuentaUpdateComponent } from './detalle-precuenta-update.component';

describe('DetallePrecuenta Management Update Component', () => {
  let comp: DetallePrecuentaUpdateComponent;
  let fixture: ComponentFixture<DetallePrecuentaUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let detallePrecuentaFormService: DetallePrecuentaFormService;
  let detallePrecuentaService: DetallePrecuentaService;
  let ordenService: OrdenService;
  let autorizacionService: AutorizacionService;
  let regVentaService: RegVentaService;
  let precuentaService: PrecuentaService;
  let detalleOrdenService: DetalleOrdenService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [DetallePrecuentaUpdateComponent],
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
      .overrideTemplate(DetallePrecuentaUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(DetallePrecuentaUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    detallePrecuentaFormService = TestBed.inject(DetallePrecuentaFormService);
    detallePrecuentaService = TestBed.inject(DetallePrecuentaService);
    ordenService = TestBed.inject(OrdenService);
    autorizacionService = TestBed.inject(AutorizacionService);
    regVentaService = TestBed.inject(RegVentaService);
    precuentaService = TestBed.inject(PrecuentaService);
    detalleOrdenService = TestBed.inject(DetalleOrdenService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Orden query and add missing value', () => {
      const detallePrecuenta: IDetallePrecuenta = { id: 'CBA' };
      const orden: IOrden = { id: '634a4ff7-f332-43c1-89d4-5481a66c1287' };
      detallePrecuenta.orden = orden;

      const ordenCollection: IOrden[] = [{ id: '5694093d-d1c3-4380-a33a-2d3ab491ac32' }];
      jest.spyOn(ordenService, 'query').mockReturnValue(of(new HttpResponse({ body: ordenCollection })));
      const additionalOrdens = [orden];
      const expectedCollection: IOrden[] = [...additionalOrdens, ...ordenCollection];
      jest.spyOn(ordenService, 'addOrdenToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ detallePrecuenta });
      comp.ngOnInit();

      expect(ordenService.query).toHaveBeenCalled();
      expect(ordenService.addOrdenToCollectionIfMissing).toHaveBeenCalledWith(
        ordenCollection,
        ...additionalOrdens.map(expect.objectContaining)
      );
      expect(comp.ordensSharedCollection).toEqual(expectedCollection);
    });

    it('Should call Autorizacion query and add missing value', () => {
      const detallePrecuenta: IDetallePrecuenta = { id: 'CBA' };
      const autorizacion: IAutorizacion = { id: '50e35ab5-3bc3-4300-b95a-c55282f40fa2' };
      detallePrecuenta.autorizacion = autorizacion;

      const autorizacionCollection: IAutorizacion[] = [{ id: '9d728de1-218d-44fe-b5a2-170dc3b0fb44' }];
      jest.spyOn(autorizacionService, 'query').mockReturnValue(of(new HttpResponse({ body: autorizacionCollection })));
      const additionalAutorizacions = [autorizacion];
      const expectedCollection: IAutorizacion[] = [...additionalAutorizacions, ...autorizacionCollection];
      jest.spyOn(autorizacionService, 'addAutorizacionToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ detallePrecuenta });
      comp.ngOnInit();

      expect(autorizacionService.query).toHaveBeenCalled();
      expect(autorizacionService.addAutorizacionToCollectionIfMissing).toHaveBeenCalledWith(
        autorizacionCollection,
        ...additionalAutorizacions.map(expect.objectContaining)
      );
      expect(comp.autorizacionsSharedCollection).toEqual(expectedCollection);
    });

    it('Should call RegVenta query and add missing value', () => {
      const detallePrecuenta: IDetallePrecuenta = { id: 'CBA' };
      const regVenta: IRegVenta = { id: 'b791e37d-b69f-4306-8e27-62e3a8474997' };
      detallePrecuenta.regVenta = regVenta;

      const regVentaCollection: IRegVenta[] = [{ id: '8286a2cb-b8d9-4b31-bb26-8e6e5d76b5e0' }];
      jest.spyOn(regVentaService, 'query').mockReturnValue(of(new HttpResponse({ body: regVentaCollection })));
      const additionalRegVentas = [regVenta];
      const expectedCollection: IRegVenta[] = [...additionalRegVentas, ...regVentaCollection];
      jest.spyOn(regVentaService, 'addRegVentaToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ detallePrecuenta });
      comp.ngOnInit();

      expect(regVentaService.query).toHaveBeenCalled();
      expect(regVentaService.addRegVentaToCollectionIfMissing).toHaveBeenCalledWith(
        regVentaCollection,
        ...additionalRegVentas.map(expect.objectContaining)
      );
      expect(comp.regVentasSharedCollection).toEqual(expectedCollection);
    });

    it('Should call Precuenta query and add missing value', () => {
      const detallePrecuenta: IDetallePrecuenta = { id: 'CBA' };
      const precuenta: IPrecuenta = { id: '5b7f495e-f5d3-484e-9832-6fd6c8eb5db3' };
      detallePrecuenta.precuenta = precuenta;

      const precuentaCollection: IPrecuenta[] = [{ id: 'c3cc0e55-9973-4556-87ce-6082844b81fa' }];
      jest.spyOn(precuentaService, 'query').mockReturnValue(of(new HttpResponse({ body: precuentaCollection })));
      const additionalPrecuentas = [precuenta];
      const expectedCollection: IPrecuenta[] = [...additionalPrecuentas, ...precuentaCollection];
      jest.spyOn(precuentaService, 'addPrecuentaToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ detallePrecuenta });
      comp.ngOnInit();

      expect(precuentaService.query).toHaveBeenCalled();
      expect(precuentaService.addPrecuentaToCollectionIfMissing).toHaveBeenCalledWith(
        precuentaCollection,
        ...additionalPrecuentas.map(expect.objectContaining)
      );
      expect(comp.precuentasSharedCollection).toEqual(expectedCollection);
    });

    it('Should call DetalleOrden query and add missing value', () => {
      const detallePrecuenta: IDetallePrecuenta = { id: 'CBA' };
      const detalleOrden: IDetalleOrden = { id: '4f804b0d-03f2-4a30-86a2-928ea196bb87' };
      detallePrecuenta.detalleOrden = detalleOrden;

      const detalleOrdenCollection: IDetalleOrden[] = [{ id: 'fd9ee0c2-5682-4d22-b21a-5a80c8786b92' }];
      jest.spyOn(detalleOrdenService, 'query').mockReturnValue(of(new HttpResponse({ body: detalleOrdenCollection })));
      const additionalDetalleOrdens = [detalleOrden];
      const expectedCollection: IDetalleOrden[] = [...additionalDetalleOrdens, ...detalleOrdenCollection];
      jest.spyOn(detalleOrdenService, 'addDetalleOrdenToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ detallePrecuenta });
      comp.ngOnInit();

      expect(detalleOrdenService.query).toHaveBeenCalled();
      expect(detalleOrdenService.addDetalleOrdenToCollectionIfMissing).toHaveBeenCalledWith(
        detalleOrdenCollection,
        ...additionalDetalleOrdens.map(expect.objectContaining)
      );
      expect(comp.detalleOrdensSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const detallePrecuenta: IDetallePrecuenta = { id: 'CBA' };
      const orden: IOrden = { id: '67398cc3-7628-43e8-8811-245338346d25' };
      detallePrecuenta.orden = orden;
      const autorizacion: IAutorizacion = { id: '45f1886e-2140-45d1-8f0c-f134c3de1374' };
      detallePrecuenta.autorizacion = autorizacion;
      const regVenta: IRegVenta = { id: '89cc6a2a-3e47-4993-8062-8aaa5f96b4e5' };
      detallePrecuenta.regVenta = regVenta;
      const precuenta: IPrecuenta = { id: '51fbdb55-c495-426c-8f83-40768da5f895' };
      detallePrecuenta.precuenta = precuenta;
      const detalleOrden: IDetalleOrden = { id: 'bfff821a-f847-42b3-8027-f0c73bfd1e53' };
      detallePrecuenta.detalleOrden = detalleOrden;

      activatedRoute.data = of({ detallePrecuenta });
      comp.ngOnInit();

      expect(comp.ordensSharedCollection).toContain(orden);
      expect(comp.autorizacionsSharedCollection).toContain(autorizacion);
      expect(comp.regVentasSharedCollection).toContain(regVenta);
      expect(comp.precuentasSharedCollection).toContain(precuenta);
      expect(comp.detalleOrdensSharedCollection).toContain(detalleOrden);
      expect(comp.detallePrecuenta).toEqual(detallePrecuenta);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDetallePrecuenta>>();
      const detallePrecuenta = { id: 'ABC' };
      jest.spyOn(detallePrecuentaFormService, 'getDetallePrecuenta').mockReturnValue(detallePrecuenta);
      jest.spyOn(detallePrecuentaService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ detallePrecuenta });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: detallePrecuenta }));
      saveSubject.complete();

      // THEN
      expect(detallePrecuentaFormService.getDetallePrecuenta).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(detallePrecuentaService.update).toHaveBeenCalledWith(expect.objectContaining(detallePrecuenta));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDetallePrecuenta>>();
      const detallePrecuenta = { id: 'ABC' };
      jest.spyOn(detallePrecuentaFormService, 'getDetallePrecuenta').mockReturnValue({ id: null });
      jest.spyOn(detallePrecuentaService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ detallePrecuenta: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: detallePrecuenta }));
      saveSubject.complete();

      // THEN
      expect(detallePrecuentaFormService.getDetallePrecuenta).toHaveBeenCalled();
      expect(detallePrecuentaService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDetallePrecuenta>>();
      const detallePrecuenta = { id: 'ABC' };
      jest.spyOn(detallePrecuentaService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ detallePrecuenta });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(detallePrecuentaService.update).toHaveBeenCalled();
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

    describe('compareAutorizacion', () => {
      it('Should forward to autorizacionService', () => {
        const entity = { id: 'ABC' };
        const entity2 = { id: 'CBA' };
        jest.spyOn(autorizacionService, 'compareAutorizacion');
        comp.compareAutorizacion(entity, entity2);
        expect(autorizacionService.compareAutorizacion).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareRegVenta', () => {
      it('Should forward to regVentaService', () => {
        const entity = { id: 'ABC' };
        const entity2 = { id: 'CBA' };
        jest.spyOn(regVentaService, 'compareRegVenta');
        comp.compareRegVenta(entity, entity2);
        expect(regVentaService.compareRegVenta).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('comparePrecuenta', () => {
      it('Should forward to precuentaService', () => {
        const entity = { id: 'ABC' };
        const entity2 = { id: 'CBA' };
        jest.spyOn(precuentaService, 'comparePrecuenta');
        comp.comparePrecuenta(entity, entity2);
        expect(precuentaService.comparePrecuenta).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareDetalleOrden', () => {
      it('Should forward to detalleOrdenService', () => {
        const entity = { id: 'ABC' };
        const entity2 = { id: 'CBA' };
        jest.spyOn(detalleOrdenService, 'compareDetalleOrden');
        comp.compareDetalleOrden(entity, entity2);
        expect(detalleOrdenService.compareDetalleOrden).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
