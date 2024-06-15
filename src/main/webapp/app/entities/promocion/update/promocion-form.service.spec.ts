import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../promocion.test-samples';

import { PromocionFormService } from './promocion-form.service';

describe('Promocion Form Service', () => {
  let service: PromocionFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PromocionFormService);
  });

  describe('Service methods', () => {
    describe('createPromocionFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createPromocionFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            tipPromocion: expect.any(Object),
            val1: expect.any(Object),
            val2: expect.any(Object),
            val3: expect.any(Object),
            val4: expect.any(Object),
            val5: expect.any(Object),
            maxProm: expect.any(Object),
            fecIniVig: expect.any(Object),
            fecFinVig: expect.any(Object),
            comentarios: expect.any(Object),
            estado: expect.any(Object),
            version: expect.any(Object),
            indDel: expect.any(Object),
            fecCrea: expect.any(Object),
            usuCrea: expect.any(Object),
            ipCrea: expect.any(Object),
            fecModif: expect.any(Object),
            usuModif: expect.any(Object),
            ipModif: expect.any(Object),
            autorizacion: expect.any(Object),
          })
        );
      });

      it('passing IPromocion should create a new form with FormGroup', () => {
        const formGroup = service.createPromocionFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            tipPromocion: expect.any(Object),
            val1: expect.any(Object),
            val2: expect.any(Object),
            val3: expect.any(Object),
            val4: expect.any(Object),
            val5: expect.any(Object),
            maxProm: expect.any(Object),
            fecIniVig: expect.any(Object),
            fecFinVig: expect.any(Object),
            comentarios: expect.any(Object),
            estado: expect.any(Object),
            version: expect.any(Object),
            indDel: expect.any(Object),
            fecCrea: expect.any(Object),
            usuCrea: expect.any(Object),
            ipCrea: expect.any(Object),
            fecModif: expect.any(Object),
            usuModif: expect.any(Object),
            ipModif: expect.any(Object),
            autorizacion: expect.any(Object),
          })
        );
      });
    });

    describe('getPromocion', () => {
      it('should return NewPromocion for default Promocion initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createPromocionFormGroup(sampleWithNewData);

        const promocion = service.getPromocion(formGroup) as any;

        expect(promocion).toMatchObject(sampleWithNewData);
      });

      it('should return NewPromocion for empty Promocion initial value', () => {
        const formGroup = service.createPromocionFormGroup();

        const promocion = service.getPromocion(formGroup) as any;

        expect(promocion).toMatchObject({});
      });

      it('should return IPromocion', () => {
        const formGroup = service.createPromocionFormGroup(sampleWithRequiredData);

        const promocion = service.getPromocion(formGroup) as any;

        expect(promocion).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IPromocion should not enable id FormControl', () => {
        const formGroup = service.createPromocionFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewPromocion should disable id FormControl', () => {
        const formGroup = service.createPromocionFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
