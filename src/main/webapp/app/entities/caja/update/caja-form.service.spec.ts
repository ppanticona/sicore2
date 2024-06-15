import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../caja.test-samples';

import { CajaFormService } from './caja-form.service';

describe('Caja Form Service', () => {
  let service: CajaFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CajaFormService);
  });

  describe('Service methods', () => {
    describe('createCajaFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createCajaFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            tipCaja: expect.any(Object),
            descripcion: expect.any(Object),
            estado: expect.any(Object),
            version: expect.any(Object),
            indDel: expect.any(Object),
            fecCrea: expect.any(Object),
            usuCrea: expect.any(Object),
            ipCrea: expect.any(Object),
            fecModif: expect.any(Object),
            usuModif: expect.any(Object),
            ipModif: expect.any(Object),
          })
        );
      });

      it('passing ICaja should create a new form with FormGroup', () => {
        const formGroup = service.createCajaFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            tipCaja: expect.any(Object),
            descripcion: expect.any(Object),
            estado: expect.any(Object),
            version: expect.any(Object),
            indDel: expect.any(Object),
            fecCrea: expect.any(Object),
            usuCrea: expect.any(Object),
            ipCrea: expect.any(Object),
            fecModif: expect.any(Object),
            usuModif: expect.any(Object),
            ipModif: expect.any(Object),
          })
        );
      });
    });

    describe('getCaja', () => {
      it('should return NewCaja for default Caja initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createCajaFormGroup(sampleWithNewData);

        const caja = service.getCaja(formGroup) as any;

        expect(caja).toMatchObject(sampleWithNewData);
      });

      it('should return NewCaja for empty Caja initial value', () => {
        const formGroup = service.createCajaFormGroup();

        const caja = service.getCaja(formGroup) as any;

        expect(caja).toMatchObject({});
      });

      it('should return ICaja', () => {
        const formGroup = service.createCajaFormGroup(sampleWithRequiredData);

        const caja = service.getCaja(formGroup) as any;

        expect(caja).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ICaja should not enable id FormControl', () => {
        const formGroup = service.createCajaFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewCaja should disable id FormControl', () => {
        const formGroup = service.createCajaFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
