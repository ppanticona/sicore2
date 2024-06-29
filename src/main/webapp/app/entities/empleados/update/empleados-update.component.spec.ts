import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { EmpleadosFormService } from './empleados-form.service';
import { EmpleadosService } from '../service/empleados.service';
import { IEmpleados } from '../empleados.model';

import { IUser } from 'app/entities/user/user.model';
import { UserService } from 'app/entities/user/user.service';

import { EmpleadosUpdateComponent } from './empleados-update.component';

describe('Empleados Management Update Component', () => {
  let comp: EmpleadosUpdateComponent;
  let fixture: ComponentFixture<EmpleadosUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let empleadosFormService: EmpleadosFormService;
  let empleadosService: EmpleadosService;
  let userService: UserService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [EmpleadosUpdateComponent],
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
      .overrideTemplate(EmpleadosUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(EmpleadosUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    empleadosFormService = TestBed.inject(EmpleadosFormService);
    empleadosService = TestBed.inject(EmpleadosService);
    userService = TestBed.inject(UserService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call User query and add missing value', () => {
      const empleados: IEmpleados = { id: 'CBA' };
      const user: IUser = { id: '980dfa97-8671-4e5b-ba9c-51186543ddf1' };
      empleados.user = user;

      const userCollection: IUser[] = [{ id: 'a2df253d-5f1c-4ef2-9f32-33ec89685614' }];
      jest.spyOn(userService, 'query').mockReturnValue(of(new HttpResponse({ body: userCollection })));
      const additionalUsers = [user];
      const expectedCollection: IUser[] = [...additionalUsers, ...userCollection];
      jest.spyOn(userService, 'addUserToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ empleados });
      comp.ngOnInit();

      expect(userService.query).toHaveBeenCalled();
      expect(userService.addUserToCollectionIfMissing).toHaveBeenCalledWith(
        userCollection,
        ...additionalUsers.map(expect.objectContaining)
      );
      expect(comp.usersSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const empleados: IEmpleados = { id: 'CBA' };
      const user: IUser = { id: '06fc1408-6e7f-406d-a56e-4e37a0697d25' };
      empleados.user = user;

      activatedRoute.data = of({ empleados });
      comp.ngOnInit();

      expect(comp.usersSharedCollection).toContain(user);
      expect(comp.empleados).toEqual(empleados);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IEmpleados>>();
      const empleados = { id: 'ABC' };
      jest.spyOn(empleadosFormService, 'getEmpleados').mockReturnValue(empleados);
      jest.spyOn(empleadosService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ empleados });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: empleados }));
      saveSubject.complete();

      // THEN
      expect(empleadosFormService.getEmpleados).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(empleadosService.update).toHaveBeenCalledWith(expect.objectContaining(empleados));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IEmpleados>>();
      const empleados = { id: 'ABC' };
      jest.spyOn(empleadosFormService, 'getEmpleados').mockReturnValue({ id: null });
      jest.spyOn(empleadosService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ empleados: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: empleados }));
      saveSubject.complete();

      // THEN
      expect(empleadosFormService.getEmpleados).toHaveBeenCalled();
      expect(empleadosService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IEmpleados>>();
      const empleados = { id: 'ABC' };
      jest.spyOn(empleadosService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ empleados });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(empleadosService.update).toHaveBeenCalled();
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
