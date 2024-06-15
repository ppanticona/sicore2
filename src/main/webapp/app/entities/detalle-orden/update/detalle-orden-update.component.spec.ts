import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { DetalleOrdenFormService } from './detalle-orden-form.service';
import { DetalleOrdenService } from '../service/detalle-orden.service';
import { IDetalleOrden } from '../detalle-orden.model';
import { IOrden } from 'app/entities/orden/orden.model';
import { OrdenService } from 'app/entities/orden/service/orden.service';
import { IPromocion } from 'app/entities/promocion/promocion.model';
import { PromocionService } from 'app/entities/promocion/service/promocion.service';
import { IAutorizacion } from 'app/entities/autorizacion/autorizacion.model';
import { AutorizacionService } from 'app/entities/autorizacion/service/autorizacion.service';
import { IProducto } from 'app/entities/producto/producto.model';
import { ProductoService } from 'app/entities/producto/service/producto.service';

import { DetalleOrdenUpdateComponent } from './detalle-orden-update.component';

describe('DetalleOrden Management Update Component', () => {
  let comp: DetalleOrdenUpdateComponent;
  let fixture: ComponentFixture<DetalleOrdenUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let detalleOrdenFormService: DetalleOrdenFormService;
  let detalleOrdenService: DetalleOrdenService;
  let ordenService: OrdenService;
  let promocionService: PromocionService;
  let autorizacionService: AutorizacionService;
  let productoService: ProductoService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [DetalleOrdenUpdateComponent],
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
      .overrideTemplate(DetalleOrdenUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(DetalleOrdenUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    detalleOrdenFormService = TestBed.inject(DetalleOrdenFormService);
    detalleOrdenService = TestBed.inject(DetalleOrdenService);
    ordenService = TestBed.inject(OrdenService);
    promocionService = TestBed.inject(PromocionService);
    autorizacionService = TestBed.inject(AutorizacionService);
    productoService = TestBed.inject(ProductoService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Orden query and add missing value', () => {
      const detalleOrden: IDetalleOrden = { id: 'CBA' };
      const orden: IOrden = { id: 'bde9c540-3ed3-4c64-9563-7b5d3fb463d7' };
      detalleOrden.orden = orden;

      const ordenCollection: IOrden[] = [{ id: 'e04944a1-97d7-4a55-a2ca-c7d1f152f429' }];
      jest.spyOn(ordenService, 'query').mockReturnValue(of(new HttpResponse({ body: ordenCollection })));
      const additionalOrdens = [orden];
      const expectedCollection: IOrden[] = [...additionalOrdens, ...ordenCollection];
      jest.spyOn(ordenService, 'addOrdenToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ detalleOrden });
      comp.ngOnInit();

      expect(ordenService.query).toHaveBeenCalled();
      expect(ordenService.addOrdenToCollectionIfMissing).toHaveBeenCalledWith(
        ordenCollection,
        ...additionalOrdens.map(expect.objectContaining)
      );
      expect(comp.ordensSharedCollection).toEqual(expectedCollection);
    });

    it('Should call Promocion query and add missing value', () => {
      const detalleOrden: IDetalleOrden = { id: 'CBA' };
      const promocion: IPromocion = { id: 'bd990589-1fec-44fc-a175-d208375d7834' };
      detalleOrden.promocion = promocion;

      const promocionCollection: IPromocion[] = [{ id: 'd58375d1-6467-4e74-9ea9-7a6da300504d' }];
      jest.spyOn(promocionService, 'query').mockReturnValue(of(new HttpResponse({ body: promocionCollection })));
      const additionalPromocions = [promocion];
      const expectedCollection: IPromocion[] = [...additionalPromocions, ...promocionCollection];
      jest.spyOn(promocionService, 'addPromocionToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ detalleOrden });
      comp.ngOnInit();

      expect(promocionService.query).toHaveBeenCalled();
      expect(promocionService.addPromocionToCollectionIfMissing).toHaveBeenCalledWith(
        promocionCollection,
        ...additionalPromocions.map(expect.objectContaining)
      );
      expect(comp.promocionsSharedCollection).toEqual(expectedCollection);
    });

    it('Should call Autorizacion query and add missing value', () => {
      const detalleOrden: IDetalleOrden = { id: 'CBA' };
      const autorizacion: IAutorizacion = { id: '6a90bfba-6169-4c69-a45a-887fae7524f3' };
      detalleOrden.autorizacion = autorizacion;

      const autorizacionCollection: IAutorizacion[] = [{ id: '4291c260-907a-41e3-88b1-ed31c4871712' }];
      jest.spyOn(autorizacionService, 'query').mockReturnValue(of(new HttpResponse({ body: autorizacionCollection })));
      const additionalAutorizacions = [autorizacion];
      const expectedCollection: IAutorizacion[] = [...additionalAutorizacions, ...autorizacionCollection];
      jest.spyOn(autorizacionService, 'addAutorizacionToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ detalleOrden });
      comp.ngOnInit();

      expect(autorizacionService.query).toHaveBeenCalled();
      expect(autorizacionService.addAutorizacionToCollectionIfMissing).toHaveBeenCalledWith(
        autorizacionCollection,
        ...additionalAutorizacions.map(expect.objectContaining)
      );
      expect(comp.autorizacionsSharedCollection).toEqual(expectedCollection);
    });

    it('Should call Producto query and add missing value', () => {
      const detalleOrden: IDetalleOrden = { id: 'CBA' };
      const producto: IProducto = { id: '5b811327-cce5-4f55-9efc-897084fb4408' };
      detalleOrden.producto = producto;

      const productoCollection: IProducto[] = [{ id: '290577f5-453f-44f3-b459-3c59135679cd' }];
      jest.spyOn(productoService, 'query').mockReturnValue(of(new HttpResponse({ body: productoCollection })));
      const additionalProductos = [producto];
      const expectedCollection: IProducto[] = [...additionalProductos, ...productoCollection];
      jest.spyOn(productoService, 'addProductoToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ detalleOrden });
      comp.ngOnInit();

      expect(productoService.query).toHaveBeenCalled();
      expect(productoService.addProductoToCollectionIfMissing).toHaveBeenCalledWith(
        productoCollection,
        ...additionalProductos.map(expect.objectContaining)
      );
      expect(comp.productosSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const detalleOrden: IDetalleOrden = { id: 'CBA' };
      const orden: IOrden = { id: 'cf9db913-b1b3-4810-9db6-04107f298cef' };
      detalleOrden.orden = orden;
      const promocion: IPromocion = { id: '5244545c-6eff-4e13-94f3-430acfe345af' };
      detalleOrden.promocion = promocion;
      const autorizacion: IAutorizacion = { id: '4bdda8a2-b640-49e6-9931-494012d3b480' };
      detalleOrden.autorizacion = autorizacion;
      const producto: IProducto = { id: 'b846fb2a-806e-4bc0-8617-79a326cd5f81' };
      detalleOrden.producto = producto;

      activatedRoute.data = of({ detalleOrden });
      comp.ngOnInit();

      expect(comp.ordensSharedCollection).toContain(orden);
      expect(comp.promocionsSharedCollection).toContain(promocion);
      expect(comp.autorizacionsSharedCollection).toContain(autorizacion);
      expect(comp.productosSharedCollection).toContain(producto);
      expect(comp.detalleOrden).toEqual(detalleOrden);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDetalleOrden>>();
      const detalleOrden = { id: 'ABC' };
      jest.spyOn(detalleOrdenFormService, 'getDetalleOrden').mockReturnValue(detalleOrden);
      jest.spyOn(detalleOrdenService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ detalleOrden });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: detalleOrden }));
      saveSubject.complete();

      // THEN
      expect(detalleOrdenFormService.getDetalleOrden).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(detalleOrdenService.update).toHaveBeenCalledWith(expect.objectContaining(detalleOrden));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDetalleOrden>>();
      const detalleOrden = { id: 'ABC' };
      jest.spyOn(detalleOrdenFormService, 'getDetalleOrden').mockReturnValue({ id: null });
      jest.spyOn(detalleOrdenService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ detalleOrden: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: detalleOrden }));
      saveSubject.complete();

      // THEN
      expect(detalleOrdenFormService.getDetalleOrden).toHaveBeenCalled();
      expect(detalleOrdenService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDetalleOrden>>();
      const detalleOrden = { id: 'ABC' };
      jest.spyOn(detalleOrdenService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ detalleOrden });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(detalleOrdenService.update).toHaveBeenCalled();
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

    describe('comparePromocion', () => {
      it('Should forward to promocionService', () => {
        const entity = { id: 'ABC' };
        const entity2 = { id: 'CBA' };
        jest.spyOn(promocionService, 'comparePromocion');
        comp.comparePromocion(entity, entity2);
        expect(promocionService.comparePromocion).toHaveBeenCalledWith(entity, entity2);
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

    describe('compareProducto', () => {
      it('Should forward to productoService', () => {
        const entity = { id: 'ABC' };
        const entity2 = { id: 'CBA' };
        jest.spyOn(productoService, 'compareProducto');
        comp.compareProducto(entity, entity2);
        expect(productoService.compareProducto).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
