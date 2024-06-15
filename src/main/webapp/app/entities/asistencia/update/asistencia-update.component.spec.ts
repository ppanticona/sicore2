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
import { IEmpleados } from 'app/entities/empleados/empleados.model';
import { EmpleadosService } from 'app/entities/empleados/service/empleados.service';

import { AsistenciaUpdateComponent } from './asistencia-update.component';

describe('Asistencia Management Update Component', () => {
  let comp: AsistenciaUpdateComponent;
  let fixture: ComponentFixture<AsistenciaUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let asistenciaFormService: AsistenciaFormService;
  let asistenciaService: AsistenciaService;
  let empleadosService: EmpleadosService;

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
    empleadosService = TestBed.inject(EmpleadosService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Empleados query and add missing value', () => {
      const asistencia: IAsistencia = { id: 'CBA' };
      const userId: IEmpleados = { id: '994db739-9c91-4e98-89e2-21809aba26f0' };
      asistencia.userId = userId;

      const empleadosCollection: IEmpleados[] = [{ id: 'ea1bf707-5b32-4297-b2ad-3b346873d250' }];
      jest.spyOn(empleadosService, 'query').mockReturnValue(of(new HttpResponse({ body: empleadosCollection })));
      const additionalEmpleados = [userId];
      const expectedCollection: IEmpleados[] = [...additionalEmpleados, ...empleadosCollection];
      jest.spyOn(empleadosService, 'addEmpleadosToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ asistencia });
      comp.ngOnInit();

      expect(empleadosService.query).toHaveBeenCalled();
      expect(empleadosService.addEmpleadosToCollectionIfMissing).toHaveBeenCalledWith(
        empleadosCollection,
        ...additionalEmpleados.map(expect.objectContaining)
      );
      expect(comp.empleadosSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const asistencia: IAsistencia = { id: 'CBA' };
      const userId: IEmpleados = { id: '9c332313-10ad-4559-adb2-58a6661e5dd1' };
      asistencia.userId = userId;

      activatedRoute.data = of({ asistencia });
      comp.ngOnInit();

      expect(comp.empleadosSharedCollection).toContain(userId);
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
