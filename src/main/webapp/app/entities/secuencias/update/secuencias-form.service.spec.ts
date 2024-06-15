import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../secuencias.test-samples';

import { SecuenciasFormService } from './secuencias-form.service';

describe('Secuencias Form Service', () => {
  let service: SecuenciasFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SecuenciasFormService);
  });

  describe('Service methods', () => {
    describe('createSecuenciasFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createSecuenciasFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            seqid: expect.any(Object),
            descripcion: expect.any(Object),
            sequence: expect.any(Object),
          })
        );
      });

      it('passing ISecuencias should create a new form with FormGroup', () => {
        const formGroup = service.createSecuenciasFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            seqid: expect.any(Object),
            descripcion: expect.any(Object),
            sequence: expect.any(Object),
          })
        );
      });
    });

    describe('getSecuencias', () => {
      it('should return NewSecuencias for default Secuencias initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createSecuenciasFormGroup(sampleWithNewData);

        const secuencias = service.getSecuencias(formGroup) as any;

        expect(secuencias).toMatchObject(sampleWithNewData);
      });

      it('should return NewSecuencias for empty Secuencias initial value', () => {
        const formGroup = service.createSecuenciasFormGroup();

        const secuencias = service.getSecuencias(formGroup) as any;

        expect(secuencias).toMatchObject({});
      });

      it('should return ISecuencias', () => {
        const formGroup = service.createSecuenciasFormGroup(sampleWithRequiredData);

        const secuencias = service.getSecuencias(formGroup) as any;

        expect(secuencias).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ISecuencias should not enable id FormControl', () => {
        const formGroup = service.createSecuenciasFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewSecuencias should disable id FormControl', () => {
        const formGroup = service.createSecuenciasFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
