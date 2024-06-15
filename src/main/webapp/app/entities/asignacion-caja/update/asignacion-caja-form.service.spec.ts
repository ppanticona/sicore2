import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../asignacion-caja.test-samples';

import { AsignacionCajaFormService } from './asignacion-caja-form.service';

describe('AsignacionCaja Form Service', () => {
  let service: AsignacionCajaFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AsignacionCajaFormService);
  });

  describe('Service methods', () => {
    describe('createAsignacionCajaFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createAsignacionCajaFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            codAsignacion: expect.any(Object),
            mntoInicialSoles: expect.any(Object),
            mntoFinalSoles: expect.any(Object),
            montoMaximoSoles: expect.any(Object),
            diferenciaSoles: expect.any(Object),
            mntoInicialDolares: expect.any(Object),
            mntoFinalDolares: expect.any(Object),
            montoMaximoDolares: expect.any(Object),
            diferenciaDolares: expect.any(Object),
            observacionApertura: expect.any(Object),
            observacionCierre: expect.any(Object),
            fecAsignacion: expect.any(Object),
            estado: expect.any(Object),
            version: expect.any(Object),
            indDel: expect.any(Object),
            fecCrea: expect.any(Object),
            usuCrea: expect.any(Object),
            ipCrea: expect.any(Object),
            fecModif: expect.any(Object),
            usuModif: expect.any(Object),
            ipModif: expect.any(Object),
            userId: expect.any(Object),
            caja: expect.any(Object),
          })
        );
      });

      it('passing IAsignacionCaja should create a new form with FormGroup', () => {
        const formGroup = service.createAsignacionCajaFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            codAsignacion: expect.any(Object),
            mntoInicialSoles: expect.any(Object),
            mntoFinalSoles: expect.any(Object),
            montoMaximoSoles: expect.any(Object),
            diferenciaSoles: expect.any(Object),
            mntoInicialDolares: expect.any(Object),
            mntoFinalDolares: expect.any(Object),
            montoMaximoDolares: expect.any(Object),
            diferenciaDolares: expect.any(Object),
            observacionApertura: expect.any(Object),
            observacionCierre: expect.any(Object),
            fecAsignacion: expect.any(Object),
            estado: expect.any(Object),
            version: expect.any(Object),
            indDel: expect.any(Object),
            fecCrea: expect.any(Object),
            usuCrea: expect.any(Object),
            ipCrea: expect.any(Object),
            fecModif: expect.any(Object),
            usuModif: expect.any(Object),
            ipModif: expect.any(Object),
            userId: expect.any(Object),
            caja: expect.any(Object),
          })
        );
      });
    });

    describe('getAsignacionCaja', () => {
      it('should return NewAsignacionCaja for default AsignacionCaja initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createAsignacionCajaFormGroup(sampleWithNewData);

        const asignacionCaja = service.getAsignacionCaja(formGroup) as any;

        expect(asignacionCaja).toMatchObject(sampleWithNewData);
      });

      it('should return NewAsignacionCaja for empty AsignacionCaja initial value', () => {
        const formGroup = service.createAsignacionCajaFormGroup();

        const asignacionCaja = service.getAsignacionCaja(formGroup) as any;

        expect(asignacionCaja).toMatchObject({});
      });

      it('should return IAsignacionCaja', () => {
        const formGroup = service.createAsignacionCajaFormGroup(sampleWithRequiredData);

        const asignacionCaja = service.getAsignacionCaja(formGroup) as any;

        expect(asignacionCaja).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IAsignacionCaja should not enable id FormControl', () => {
        const formGroup = service.createAsignacionCajaFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewAsignacionCaja should disable id FormControl', () => {
        const formGroup = service.createAsignacionCajaFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
