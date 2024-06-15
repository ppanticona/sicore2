import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { RegComprasFormService } from './reg-compras-form.service';
import { RegComprasService } from '../service/reg-compras.service';
import { IRegCompras } from '../reg-compras.model';

import { RegComprasUpdateComponent } from './reg-compras-update.component';

describe('RegCompras Management Update Component', () => {
  let comp: RegComprasUpdateComponent;
  let fixture: ComponentFixture<RegComprasUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let regComprasFormService: RegComprasFormService;
  let regComprasService: RegComprasService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [RegComprasUpdateComponent],
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
      .overrideTemplate(RegComprasUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(RegComprasUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    regComprasFormService = TestBed.inject(RegComprasFormService);
    regComprasService = TestBed.inject(RegComprasService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const regCompras: IRegCompras = { id: 'CBA' };

      activatedRoute.data = of({ regCompras });
      comp.ngOnInit();

      expect(comp.regCompras).toEqual(regCompras);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRegCompras>>();
      const regCompras = { id: 'ABC' };
      jest.spyOn(regComprasFormService, 'getRegCompras').mockReturnValue(regCompras);
      jest.spyOn(regComprasService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ regCompras });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: regCompras }));
      saveSubject.complete();

      // THEN
      expect(regComprasFormService.getRegCompras).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(regComprasService.update).toHaveBeenCalledWith(expect.objectContaining(regCompras));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRegCompras>>();
      const regCompras = { id: 'ABC' };
      jest.spyOn(regComprasFormService, 'getRegCompras').mockReturnValue({ id: null });
      jest.spyOn(regComprasService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ regCompras: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: regCompras }));
      saveSubject.complete();

      // THEN
      expect(regComprasFormService.getRegCompras).toHaveBeenCalled();
      expect(regComprasService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRegCompras>>();
      const regCompras = { id: 'ABC' };
      jest.spyOn(regComprasService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ regCompras });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(regComprasService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
