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

import { IUser } from 'app/entities/user/user.model';
import { UserService } from 'app/entities/user/user.service';
import { ICaja } from 'app/entities/caja/caja.model';
import { CajaService } from 'app/entities/caja/service/caja.service';

import { AsignacionCajaUpdateComponent } from './asignacion-caja-update.component';

describe('AsignacionCaja Management Update Component', () => {
  let comp: AsignacionCajaUpdateComponent;
  let fixture: ComponentFixture<AsignacionCajaUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let asignacionCajaFormService: AsignacionCajaFormService;
  let asignacionCajaService: AsignacionCajaService;
  let userService: UserService;
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
    userService = TestBed.inject(UserService);
    cajaService = TestBed.inject(CajaService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call User query and add missing value', () => {
      const asignacionCaja: IAsignacionCaja = { id: 'CBA' };
      const user: IUser = { id: 'edba8680-3c6a-405d-a60f-b03ccfba5ef7' };
      asignacionCaja.user = user;

      const userCollection: IUser[] = [{ id: '25f0b6b1-0bf1-4884-84e0-5e21f8c47e87' }];
      jest.spyOn(userService, 'query').mockReturnValue(of(new HttpResponse({ body: userCollection })));
      const additionalUsers = [user];
      const expectedCollection: IUser[] = [...additionalUsers, ...userCollection];
      jest.spyOn(userService, 'addUserToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ asignacionCaja });
      comp.ngOnInit();

      expect(userService.query).toHaveBeenCalled();
      expect(userService.addUserToCollectionIfMissing).toHaveBeenCalledWith(
        userCollection,
        ...additionalUsers.map(expect.objectContaining)
      );
      expect(comp.usersSharedCollection).toEqual(expectedCollection);
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
      const user: IUser = { id: 'e0c61b0b-3bb7-491a-aa63-ccf8ac06d387' };
      asignacionCaja.user = user;
      const caja: ICaja = { id: 'd9925a29-1995-4fe2-90ff-23ae38f39020' };
      asignacionCaja.caja = caja;

      activatedRoute.data = of({ asignacionCaja });
      comp.ngOnInit();

      expect(comp.usersSharedCollection).toContain(user);
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
    describe('compareUser', () => {
      it('Should forward to userService', () => {
        const entity = { id: 'ABC' };
        const entity2 = { id: 'CBA' };
        jest.spyOn(userService, 'compareUser');
        comp.compareUser(entity, entity2);
        expect(userService.compareUser).toHaveBeenCalledWith(entity, entity2);
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
