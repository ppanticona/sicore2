import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../parametros.test-samples';

import { ParametrosFormService } from './parametros-form.service';

describe('Parametros Form Service', () => {
  let service: ParametrosFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ParametrosFormService);
  });

  describe('Service methods', () => {
    describe('createParametrosFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createParametrosFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            codParam: expect.any(Object),
            detParam: expect.any(Object),
            primParam: expect.any(Object),
            segParam: expect.any(Object),
            tercParam: expect.any(Object),
            cuarParam: expect.any(Object),
            descripcion: expect.any(Object),
            sequence: expect.any(Object),
          })
        );
      });

      it('passing IParametros should create a new form with FormGroup', () => {
        const formGroup = service.createParametrosFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            codParam: expect.any(Object),
            detParam: expect.any(Object),
            primParam: expect.any(Object),
            segParam: expect.any(Object),
            tercParam: expect.any(Object),
            cuarParam: expect.any(Object),
            descripcion: expect.any(Object),
            sequence: expect.any(Object),
          })
        );
      });
    });

    describe('getParametros', () => {
      it('should return NewParametros for default Parametros initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createParametrosFormGroup(sampleWithNewData);

        const parametros = service.getParametros(formGroup) as any;

        expect(parametros).toMatchObject(sampleWithNewData);
      });

      it('should return NewParametros for empty Parametros initial value', () => {
        const formGroup = service.createParametrosFormGroup();

        const parametros = service.getParametros(formGroup) as any;

        expect(parametros).toMatchObject({});
      });

      it('should return IParametros', () => {
        const formGroup = service.createParametrosFormGroup(sampleWithRequiredData);

        const parametros = service.getParametros(formGroup) as any;

        expect(parametros).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IParametros should not enable id FormControl', () => {
        const formGroup = service.createParametrosFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewParametros should disable id FormControl', () => {
        const formGroup = service.createParametrosFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
