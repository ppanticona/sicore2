import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { ParametrosFormService } from './parametros-form.service';
import { ParametrosService } from '../service/parametros.service';
import { IParametros } from '../parametros.model';

import { ParametrosUpdateComponent } from './parametros-update.component';

describe('Parametros Management Update Component', () => {
  let comp: ParametrosUpdateComponent;
  let fixture: ComponentFixture<ParametrosUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let parametrosFormService: ParametrosFormService;
  let parametrosService: ParametrosService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [ParametrosUpdateComponent],
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
      .overrideTemplate(ParametrosUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ParametrosUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    parametrosFormService = TestBed.inject(ParametrosFormService);
    parametrosService = TestBed.inject(ParametrosService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const parametros: IParametros = { id: 'CBA' };

      activatedRoute.data = of({ parametros });
      comp.ngOnInit();

      expect(comp.parametros).toEqual(parametros);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IParametros>>();
      const parametros = { id: 'ABC' };
      jest.spyOn(parametrosFormService, 'getParametros').mockReturnValue(parametros);
      jest.spyOn(parametrosService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ parametros });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: parametros }));
      saveSubject.complete();

      // THEN
      expect(parametrosFormService.getParametros).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(parametrosService.update).toHaveBeenCalledWith(expect.objectContaining(parametros));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IParametros>>();
      const parametros = { id: 'ABC' };
      jest.spyOn(parametrosFormService, 'getParametros').mockReturnValue({ id: null });
      jest.spyOn(parametrosService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ parametros: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: parametros }));
      saveSubject.complete();

      // THEN
      expect(parametrosFormService.getParametros).toHaveBeenCalled();
      expect(parametrosService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IParametros>>();
      const parametros = { id: 'ABC' };
      jest.spyOn(parametrosService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ parametros });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(parametrosService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
