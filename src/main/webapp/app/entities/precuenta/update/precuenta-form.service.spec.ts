import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../precuenta.test-samples';

import { PrecuentaFormService } from './precuenta-form.service';

describe('Precuenta Form Service', () => {
  let service: PrecuentaFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PrecuentaFormService);
  });

  describe('Service methods', () => {
    describe('createPrecuentaFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createPrecuentaFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            numPrecuenta: expect.any(Object),
            observacion: expect.any(Object),
            estado: expect.any(Object),
            version: expect.any(Object),
            indDel: expect.any(Object),
            fecCrea: expect.any(Object),
            usuCrea: expect.any(Object),
            ipCrea: expect.any(Object),
            fecModif: expect.any(Object),
            usuModif: expect.any(Object),
            ipModif: expect.any(Object),
            orden: expect.any(Object),
          })
        );
      });

      it('passing IPrecuenta should create a new form with FormGroup', () => {
        const formGroup = service.createPrecuentaFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            numPrecuenta: expect.any(Object),
            observacion: expect.any(Object),
            estado: expect.any(Object),
            version: expect.any(Object),
            indDel: expect.any(Object),
            fecCrea: expect.any(Object),
            usuCrea: expect.any(Object),
            ipCrea: expect.any(Object),
            fecModif: expect.any(Object),
            usuModif: expect.any(Object),
            ipModif: expect.any(Object),
            orden: expect.any(Object),
          })
        );
      });
    });

    describe('getPrecuenta', () => {
      it('should return NewPrecuenta for default Precuenta initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createPrecuentaFormGroup(sampleWithNewData);

        const precuenta = service.getPrecuenta(formGroup) as any;

        expect(precuenta).toMatchObject(sampleWithNewData);
      });

      it('should return NewPrecuenta for empty Precuenta initial value', () => {
        const formGroup = service.createPrecuentaFormGroup();

        const precuenta = service.getPrecuenta(formGroup) as any;

        expect(precuenta).toMatchObject({});
      });

      it('should return IPrecuenta', () => {
        const formGroup = service.createPrecuentaFormGroup(sampleWithRequiredData);

        const precuenta = service.getPrecuenta(formGroup) as any;

        expect(precuenta).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IPrecuenta should not enable id FormControl', () => {
        const formGroup = service.createPrecuentaFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewPrecuenta should disable id FormControl', () => {
        const formGroup = service.createPrecuentaFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
