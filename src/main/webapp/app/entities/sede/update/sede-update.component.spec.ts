import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { SedeFormService } from './sede-form.service';
import { SedeService } from '../service/sede.service';
import { ISede } from '../sede.model';

import { SedeUpdateComponent } from './sede-update.component';

describe('Sede Management Update Component', () => {
  let comp: SedeUpdateComponent;
  let fixture: ComponentFixture<SedeUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let sedeFormService: SedeFormService;
  let sedeService: SedeService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [SedeUpdateComponent],
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
      .overrideTemplate(SedeUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(SedeUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    sedeFormService = TestBed.inject(SedeFormService);
    sedeService = TestBed.inject(SedeService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const sede: ISede = { id: 'CBA' };

      activatedRoute.data = of({ sede });
      comp.ngOnInit();

      expect(comp.sede).toEqual(sede);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISede>>();
      const sede = { id: 'ABC' };
      jest.spyOn(sedeFormService, 'getSede').mockReturnValue(sede);
      jest.spyOn(sedeService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ sede });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: sede }));
      saveSubject.complete();

      // THEN
      expect(sedeFormService.getSede).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(sedeService.update).toHaveBeenCalledWith(expect.objectContaining(sede));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISede>>();
      const sede = { id: 'ABC' };
      jest.spyOn(sedeFormService, 'getSede').mockReturnValue({ id: null });
      jest.spyOn(sedeService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ sede: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: sede }));
      saveSubject.complete();

      // THEN
      expect(sedeFormService.getSede).toHaveBeenCalled();
      expect(sedeService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISede>>();
      const sede = { id: 'ABC' };
      jest.spyOn(sedeService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ sede });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(sedeService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
