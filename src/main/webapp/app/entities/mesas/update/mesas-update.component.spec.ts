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

import { IUser } from 'app/entities/user/user.model';
import { UserService } from 'app/entities/user/user.service';

import { MesasUpdateComponent } from './mesas-update.component';

describe('Mesas Management Update Component', () => {
  let comp: MesasUpdateComponent;
  let fixture: ComponentFixture<MesasUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let mesasFormService: MesasFormService;
  let mesasService: MesasService;
  let ordenService: OrdenService;
  let sedeService: SedeService;
  let userService: UserService;

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
    userService = TestBed.inject(UserService);

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

    it('Should call User query and add missing value', () => {
      const mesas: IMesas = { id: 'CBA' };
      const user: IUser = { id: 'c797d872-f236-4fb4-a018-9c4e53532729' };
      mesas.user = user;

      const userCollection: IUser[] = [{ id: '0aab71c2-7bd9-48f2-96ae-932aa2d43759' }];
      jest.spyOn(userService, 'query').mockReturnValue(of(new HttpResponse({ body: userCollection })));
      const additionalUsers = [user];
      const expectedCollection: IUser[] = [...additionalUsers, ...userCollection];
      jest.spyOn(userService, 'addUserToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ mesas });
      comp.ngOnInit();

      expect(userService.query).toHaveBeenCalled();
      expect(userService.addUserToCollectionIfMissing).toHaveBeenCalledWith(
        userCollection,
        ...additionalUsers.map(expect.objectContaining)
      );
      expect(comp.usersSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const mesas: IMesas = { id: 'CBA' };
      const orden: IOrden = { id: '65102650-8caa-4ed1-92d5-4448938fe4cd' };
      mesas.orden = orden;
      const sede: ISede = { id: '4ac52d74-6a9c-4ebc-8b45-85055c048323' };
      mesas.sede = sede;
      const user: IUser = { id: '40b26408-0a09-4464-af1c-e8eeddaa3755' };
      mesas.user = user;

      activatedRoute.data = of({ mesas });
      comp.ngOnInit();

      expect(comp.ordensSharedCollection).toContain(orden);
      expect(comp.sedesSharedCollection).toContain(sede);
      expect(comp.usersSharedCollection).toContain(user);
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

    describe('compareUser', () => {
      it('Should forward to userService', () => {
        const entity = { id: 'ABC' };
        const entity2 = { id: 'CBA' };
        jest.spyOn(userService, 'compareUser');
        comp.compareUser(entity, entity2);
        expect(userService.compareUser).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
