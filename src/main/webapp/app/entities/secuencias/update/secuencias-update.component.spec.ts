import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { SecuenciasFormService } from './secuencias-form.service';
import { SecuenciasService } from '../service/secuencias.service';
import { ISecuencias } from '../secuencias.model';

import { SecuenciasUpdateComponent } from './secuencias-update.component';

describe('Secuencias Management Update Component', () => {
  let comp: SecuenciasUpdateComponent;
  let fixture: ComponentFixture<SecuenciasUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let secuenciasFormService: SecuenciasFormService;
  let secuenciasService: SecuenciasService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [SecuenciasUpdateComponent],
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
      .overrideTemplate(SecuenciasUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(SecuenciasUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    secuenciasFormService = TestBed.inject(SecuenciasFormService);
    secuenciasService = TestBed.inject(SecuenciasService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const secuencias: ISecuencias = { id: 'CBA' };

      activatedRoute.data = of({ secuencias });
      comp.ngOnInit();

      expect(comp.secuencias).toEqual(secuencias);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISecuencias>>();
      const secuencias = { id: 'ABC' };
      jest.spyOn(secuenciasFormService, 'getSecuencias').mockReturnValue(secuencias);
      jest.spyOn(secuenciasService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ secuencias });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: secuencias }));
      saveSubject.complete();

      // THEN
      expect(secuenciasFormService.getSecuencias).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(secuenciasService.update).toHaveBeenCalledWith(expect.objectContaining(secuencias));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISecuencias>>();
      const secuencias = { id: 'ABC' };
      jest.spyOn(secuenciasFormService, 'getSecuencias').mockReturnValue({ id: null });
      jest.spyOn(secuenciasService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ secuencias: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: secuencias }));
      saveSubject.complete();

      // THEN
      expect(secuenciasFormService.getSecuencias).toHaveBeenCalled();
      expect(secuenciasService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ISecuencias>>();
      const secuencias = { id: 'ABC' };
      jest.spyOn(secuenciasService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ secuencias });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(secuenciasService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
