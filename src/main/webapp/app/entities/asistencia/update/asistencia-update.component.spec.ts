import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { AsistenciaFormService } from './asistencia-form.service';
import { AsistenciaService } from '../service/asistencia.service';
import { IAsistencia } from '../asistencia.model';

import { IUser } from 'app/entities/user/user.model';
import { UserService } from 'app/entities/user/user.service';

import { AsistenciaUpdateComponent } from './asistencia-update.component';

describe('Asistencia Management Update Component', () => {
  let comp: AsistenciaUpdateComponent;
  let fixture: ComponentFixture<AsistenciaUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let asistenciaFormService: AsistenciaFormService;
  let asistenciaService: AsistenciaService;
  let userService: UserService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [AsistenciaUpdateComponent],
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
      .overrideTemplate(AsistenciaUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(AsistenciaUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    asistenciaFormService = TestBed.inject(AsistenciaFormService);
    asistenciaService = TestBed.inject(AsistenciaService);
    userService = TestBed.inject(UserService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call User query and add missing value', () => {
      const asistencia: IAsistencia = { id: 'CBA' };
      const user: IUser = { id: '511e09d2-ca6f-409a-b09e-86501ea6e42d' };
      asistencia.user = user;

      const userCollection: IUser[] = [{ id: 'a3fe33fd-147d-42bc-b31a-9a2568fa0de4' }];
      jest.spyOn(userService, 'query').mockReturnValue(of(new HttpResponse({ body: userCollection })));
      const additionalUsers = [user];
      const expectedCollection: IUser[] = [...additionalUsers, ...userCollection];
      jest.spyOn(userService, 'addUserToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ asistencia });
      comp.ngOnInit();

      expect(userService.query).toHaveBeenCalled();
      expect(userService.addUserToCollectionIfMissing).toHaveBeenCalledWith(
        userCollection,
        ...additionalUsers.map(expect.objectContaining)
      );
      expect(comp.usersSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const asistencia: IAsistencia = { id: 'CBA' };
      const user: IUser = { id: 'f33cc123-e5e7-4a64-a77a-ef1c7346d1f6' };
      asistencia.user = user;

      activatedRoute.data = of({ asistencia });
      comp.ngOnInit();

      expect(comp.usersSharedCollection).toContain(user);
      expect(comp.asistencia).toEqual(asistencia);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAsistencia>>();
      const asistencia = { id: 'ABC' };
      jest.spyOn(asistenciaFormService, 'getAsistencia').mockReturnValue(asistencia);
      jest.spyOn(asistenciaService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ asistencia });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: asistencia }));
      saveSubject.complete();

      // THEN
      expect(asistenciaFormService.getAsistencia).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(asistenciaService.update).toHaveBeenCalledWith(expect.objectContaining(asistencia));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAsistencia>>();
      const asistencia = { id: 'ABC' };
      jest.spyOn(asistenciaFormService, 'getAsistencia').mockReturnValue({ id: null });
      jest.spyOn(asistenciaService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ asistencia: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: asistencia }));
      saveSubject.complete();

      // THEN
      expect(asistenciaFormService.getAsistencia).toHaveBeenCalled();
      expect(asistenciaService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAsistencia>>();
      const asistencia = { id: 'ABC' };
      jest.spyOn(asistenciaService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ asistencia });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(asistenciaService.update).toHaveBeenCalled();
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
  });
});
