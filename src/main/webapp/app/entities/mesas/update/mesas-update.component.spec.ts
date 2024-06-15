import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { MesasFormService } from './mesas-form.service';
import { MesasService } from '../service/mesas.service';
import { IMesas } from '../mesas.model';
import { IOrden } from 'app/entities/orden/orden.model';
import { OrdenService } from 'app/entities/orden/service/orden.service';
import { ISede } from 'app/entities/sede/sede.model';
import { SedeService } from 'app/entities/sede/service/sede.service';
import { IEmpleados } from 'app/entities/empleados/empleados.model';
import { EmpleadosService } from 'app/entities/empleados/service/empleados.service';

import { MesasUpdateComponent } from './mesas-update.component';

describe('Mesas Management Update Component', () => {
  let comp: MesasUpdateComponent;
  let fixture: ComponentFixture<MesasUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let mesasFormService: MesasFormService;
  let mesasService: MesasService;
  let ordenService: OrdenService;
  let sedeService: SedeService;
  let empleadosService: EmpleadosService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [MesasUpdateComponent],
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
      .overrideTemplate(MesasUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(MesasUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    mesasFormService = TestBed.inject(MesasFormService);
    mesasService = TestBed.inject(MesasService);
    ordenService = TestBed.inject(OrdenService);
    sedeService = TestBed.inject(SedeService);
    empleadosService = TestBed.inject(EmpleadosService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Orden query and add missing value', () => {
      const mesas: IMesas = { id: 'CBA' };
      const orden: IOrden = { id: '082b1647-8ab9-4724-9e11-c6775e12eea9' };
      mesas.orden = orden;

      const ordenCollection: IOrden[] = [{ id: 'ff466a8c-bc9d-403d-a4b3-f35ee20ae1c1' }];
      jest.spyOn(ordenService, 'query').mockReturnValue(of(new HttpResponse({ body: ordenCollection })));
      const additionalOrdens = [orden];
      const expectedCollection: IOrden[] = [...additionalOrdens, ...ordenCollection];
      jest.spyOn(ordenService, 'addOrdenToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ mesas });
      comp.ngOnInit();

      expect(ordenService.query).toHaveBeenCalled();
      expect(ordenService.addOrdenToCollectionIfMissing).toHaveBeenCalledWith(
        ordenCollection,
        ...additionalOrdens.map(expect.objectContaining)
      );
      expect(comp.ordensSharedCollection).toEqual(expectedCollection);
    });

    it('Should call Sede query and add missing value', () => {
      const mesas: IMesas = { id: 'CBA' };
      const sede: ISede = { id: 'ca7a67ef-afff-45d2-bca9-dbd763382e00' };
      mesas.sede = sede;

      const sedeCollection: ISede[] = [{ id: 'd58c223f-8e15-415e-b6c2-96f1f7e09c13' }];
      jest.spyOn(sedeService, 'query').mockReturnValue(of(new HttpResponse({ body: sedeCollection })));
      const additionalSedes = [sede];
      const expectedCollection: ISede[] = [...additionalSedes, ...sedeCollection];
      jest.spyOn(sedeService, 'addSedeToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ mesas });
      comp.ngOnInit();

      expect(sedeService.query).toHaveBeenCalled();
      expect(sedeService.addSedeToCollectionIfMissing).toHaveBeenCalledWith(
        sedeCollection,
        ...additionalSedes.map(expect.objectContaining)
      );
      expect(comp.sedesSharedCollection).toEqual(expectedCollection);
    });

    it('Should call Empleados query and add missing value', () => {
      const mesas: IMesas = { id: 'CBA' };
      const empleado: IEmpleados = { id: '85336a2f-9ca4-4fff-b177-ee1bc5d21f46' };
      mesas.empleado = empleado;

      const empleadosCollection: IEmpleados[] = [{ id: 'ed359e2c-dc05-441c-b815-698999f53059' }];
      jest.spyOn(empleadosService, 'query').mockReturnValue(of(new HttpResponse({ body: empleadosCollection })));
      const additionalEmpleados = [empleado];
      const expectedCollection: IEmpleados[] = [...additionalEmpleados, ...empleadosCollection];
      jest.spyOn(empleadosService, 'addEmpleadosToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ mesas });
      comp.ngOnInit();

      expect(empleadosService.query).toHaveBeenCalled();
      expect(empleadosService.addEmpleadosToCollectionIfMissing).toHaveBeenCalledWith(
        empleadosCollection,
        ...additionalEmpleados.map(expect.objectContaining)
      );
      expect(comp.empleadosSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const mesas: IMesas = { id: 'CBA' };
      const orden: IOrden = { id: '65102650-8caa-4ed1-92d5-4448938fe4cd' };
      mesas.orden = orden;
      const sede: ISede = { id: '4ac52d74-6a9c-4ebc-8b45-85055c048323' };
      mesas.sede = sede;
      const empleado: IEmpleados = { id: '181ba636-7def-4f5d-bfe4-b1f72f4e2d9b' };
      mesas.empleado = empleado;

      activatedRoute.data = of({ mesas });
      comp.ngOnInit();

      expect(comp.ordensSharedCollection).toContain(orden);
      expect(comp.sedesSharedCollection).toContain(sede);
      expect(comp.empleadosSharedCollection).toContain(empleado);
      expect(comp.mesas).toEqual(mesas);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IMesas>>();
      const mesas = { id: 'ABC' };
      jest.spyOn(mesasFormService, 'getMesas').mockReturnValue(mesas);
      jest.spyOn(mesasService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ mesas });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: mesas }));
      saveSubject.complete();

      // THEN
      expect(mesasFormService.getMesas).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(mesasService.update).toHaveBeenCalledWith(expect.objectContaining(mesas));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IMesas>>();
      const mesas = { id: 'ABC' };
      jest.spyOn(mesasFormService, 'getMesas').mockReturnValue({ id: null });
      jest.spyOn(mesasService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ mesas: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: mesas }));
      saveSubject.complete();

      // THEN
      expect(mesasFormService.getMesas).toHaveBeenCalled();
      expect(mesasService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IMesas>>();
      const mesas = { id: 'ABC' };
      jest.spyOn(mesasService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ mesas });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(mesasService.update).toHaveBeenCalled();
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

    describe('compareSede', () => {
      it('Should forward to sedeService', () => {
        const entity = { id: 'ABC' };
        const entity2 = { id: 'CBA' };
        jest.spyOn(sedeService, 'compareSede');
        comp.compareSede(entity, entity2);
        expect(sedeService.compareSede).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareEmpleados', () => {
      it('Should forward to empleadosService', () => {
        const entity = { id: 'ABC' };
        const entity2 = { id: 'CBA' };
        jest.spyOn(empleadosService, 'compareEmpleados');
        comp.compareEmpleados(entity, entity2);
        expect(empleadosService.compareEmpleados).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
