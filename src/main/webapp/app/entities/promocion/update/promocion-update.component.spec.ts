import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { PromocionFormService } from './promocion-form.service';
import { PromocionService } from '../service/promocion.service';
import { IPromocion } from '../promocion.model';
import { IAutorizacion } from 'app/entities/autorizacion/autorizacion.model';
import { AutorizacionService } from 'app/entities/autorizacion/service/autorizacion.service';

import { PromocionUpdateComponent } from './promocion-update.component';

describe('Promocion Management Update Component', () => {
  let comp: PromocionUpdateComponent;
  let fixture: ComponentFixture<PromocionUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let promocionFormService: PromocionFormService;
  let promocionService: PromocionService;
  let autorizacionService: AutorizacionService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [PromocionUpdateComponent],
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
      .overrideTemplate(PromocionUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(PromocionUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    promocionFormService = TestBed.inject(PromocionFormService);
    promocionService = TestBed.inject(PromocionService);
    autorizacionService = TestBed.inject(AutorizacionService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Autorizacion query and add missing value', () => {
      const promocion: IPromocion = { id: 'CBA' };
      const autorizacion: IAutorizacion = { id: 'c5d2664e-c176-48b2-a862-ad838ed70a1f' };
      promocion.autorizacion = autorizacion;

      const autorizacionCollection: IAutorizacion[] = [{ id: '16543b8f-9cbd-4ca5-9fa2-5bfadb3f5880' }];
      jest.spyOn(autorizacionService, 'query').mockReturnValue(of(new HttpResponse({ body: autorizacionCollection })));
      const additionalAutorizacions = [autorizacion];
      const expectedCollection: IAutorizacion[] = [...additionalAutorizacions, ...autorizacionCollection];
      jest.spyOn(autorizacionService, 'addAutorizacionToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ promocion });
      comp.ngOnInit();

      expect(autorizacionService.query).toHaveBeenCalled();
      expect(autorizacionService.addAutorizacionToCollectionIfMissing).toHaveBeenCalledWith(
        autorizacionCollection,
        ...additionalAutorizacions.map(expect.objectContaining)
      );
      expect(comp.autorizacionsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const promocion: IPromocion = { id: 'CBA' };
      const autorizacion: IAutorizacion = { id: 'e8117e6a-c75b-4618-9db9-ca3833dd36a9' };
      promocion.autorizacion = autorizacion;

      activatedRoute.data = of({ promocion });
      comp.ngOnInit();

      expect(comp.autorizacionsSharedCollection).toContain(autorizacion);
      expect(comp.promocion).toEqual(promocion);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IPromocion>>();
      const promocion = { id: 'ABC' };
      jest.spyOn(promocionFormService, 'getPromocion').mockReturnValue(promocion);
      jest.spyOn(promocionService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ promocion });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: promocion }));
      saveSubject.complete();

      // THEN
      expect(promocionFormService.getPromocion).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(promocionService.update).toHaveBeenCalledWith(expect.objectContaining(promocion));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IPromocion>>();
      const promocion = { id: 'ABC' };
      jest.spyOn(promocionFormService, 'getPromocion').mockReturnValue({ id: null });
      jest.spyOn(promocionService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ promocion: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: promocion }));
      saveSubject.complete();

      // THEN
      expect(promocionFormService.getPromocion).toHaveBeenCalled();
      expect(promocionService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IPromocion>>();
      const promocion = { id: 'ABC' };
      jest.spyOn(promocionService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ promocion });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(promocionService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareAutorizacion', () => {
      it('Should forward to autorizacionService', () => {
        const entity = { id: 'ABC' };
        const entity2 = { id: 'CBA' };
        jest.spyOn(autorizacionService, 'compareAutorizacion');
        comp.compareAutorizacion(entity, entity2);
        expect(autorizacionService.compareAutorizacion).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
