import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { CajaFormService } from './caja-form.service';
import { CajaService } from '../service/caja.service';
import { ICaja } from '../caja.model';

import { CajaUpdateComponent } from './caja-update.component';

describe('Caja Management Update Component', () => {
  let comp: CajaUpdateComponent;
  let fixture: ComponentFixture<CajaUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let cajaFormService: CajaFormService;
  let cajaService: CajaService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [CajaUpdateComponent],
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
      .overrideTemplate(CajaUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(CajaUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    cajaFormService = TestBed.inject(CajaFormService);
    cajaService = TestBed.inject(CajaService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const caja: ICaja = { id: 'CBA' };

      activatedRoute.data = of({ caja });
      comp.ngOnInit();

      expect(comp.caja).toEqual(caja);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICaja>>();
      const caja = { id: 'ABC' };
      jest.spyOn(cajaFormService, 'getCaja').mockReturnValue(caja);
      jest.spyOn(cajaService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ caja });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: caja }));
      saveSubject.complete();

      // THEN
      expect(cajaFormService.getCaja).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(cajaService.update).toHaveBeenCalledWith(expect.objectContaining(caja));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICaja>>();
      const caja = { id: 'ABC' };
      jest.spyOn(cajaFormService, 'getCaja').mockReturnValue({ id: null });
      jest.spyOn(cajaService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ caja: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: caja }));
      saveSubject.complete();

      // THEN
      expect(cajaFormService.getCaja).toHaveBeenCalled();
      expect(cajaService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICaja>>();
      const caja = { id: 'ABC' };
      jest.spyOn(cajaService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ caja });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(cajaService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
