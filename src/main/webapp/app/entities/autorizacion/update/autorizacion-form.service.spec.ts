import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../autorizacion.test-samples';

import { AutorizacionFormService } from './autorizacion-form.service';

describe('Autorizacion Form Service', () => {
  let service: AutorizacionFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AutorizacionFormService);
  });

  describe('Service methods', () => {
    describe('createAutorizacionFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createAutorizacionFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            tipAutorizacion: expect.any(Object),
            subTipAutorizacion: expect.any(Object),
            concepto: expect.any(Object),
            comentario: expect.any(Object),
            monto: expect.any(Object),
            moneda: expect.any(Object),
            token: expect.any(Object),
            fecIniVig: expect.any(Object),
            fecFinVig: expect.any(Object),
            estado: expect.any(Object),
            version: expect.any(Object),
            indDel: expect.any(Object),
            fecCrea: expect.any(Object),
            usuCrea: expect.any(Object),
            ipCrea: expect.any(Object),
            fecModif: expect.any(Object),
            usuModif: expect.any(Object),
            ipModif: expect.any(Object),
            producto: expect.any(Object),
          })
        );
      });

      it('passing IAutorizacion should create a new form with FormGroup', () => {
        const formGroup = service.createAutorizacionFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            tipAutorizacion: expect.any(Object),
            subTipAutorizacion: expect.any(Object),
            concepto: expect.any(Object),
            comentario: expect.any(Object),
            monto: expect.any(Object),
            moneda: expect.any(Object),
            token: expect.any(Object),
            fecIniVig: expect.any(Object),
            fecFinVig: expect.any(Object),
            estado: expect.any(Object),
            version: expect.any(Object),
            indDel: expect.any(Object),
            fecCrea: expect.any(Object),
            usuCrea: expect.any(Object),
            ipCrea: expect.any(Object),
            fecModif: expect.any(Object),
            usuModif: expect.any(Object),
            ipModif: expect.any(Object),
            producto: expect.any(Object),
          })
        );
      });
    });

    describe('getAutorizacion', () => {
      it('should return NewAutorizacion for default Autorizacion initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createAutorizacionFormGroup(sampleWithNewData);

        const autorizacion = service.getAutorizacion(formGroup) as any;

        expect(autorizacion).toMatchObject(sampleWithNewData);
      });

      it('should return NewAutorizacion for empty Autorizacion initial value', () => {
        const formGroup = service.createAutorizacionFormGroup();

        const autorizacion = service.getAutorizacion(formGroup) as any;

        expect(autorizacion).toMatchObject({});
      });

      it('should return IAutorizacion', () => {
        const formGroup = service.createAutorizacionFormGroup(sampleWithRequiredData);

        const autorizacion = service.getAutorizacion(formGroup) as any;

        expect(autorizacion).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IAutorizacion should not enable id FormControl', () => {
        const formGroup = service.createAutorizacionFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewAutorizacion should disable id FormControl', () => {
        const formGroup = service.createAutorizacionFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
