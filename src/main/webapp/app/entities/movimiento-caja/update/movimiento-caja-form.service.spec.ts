import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../movimiento-caja.test-samples';

import { MovimientoCajaFormService } from './movimiento-caja-form.service';

describe('MovimientoCaja Form Service', () => {
  let service: MovimientoCajaFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MovimientoCajaFormService);
  });

  describe('Service methods', () => {
    describe('createMovimientoCajaFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createMovimientoCajaFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            tipMovimiento: expect.any(Object),
            concepto: expect.any(Object),
            monto: expect.any(Object),
            tipMoneda: expect.any(Object),
            formPago: expect.any(Object),
            comprobante: expect.any(Object),
            fecMovimiento: expect.any(Object),
            version: expect.any(Object),
            indDel: expect.any(Object),
            fecCrea: expect.any(Object),
            usuCrea: expect.any(Object),
            ipCrea: expect.any(Object),
            fecModif: expect.any(Object),
            usuModif: expect.any(Object),
            ipModif: expect.any(Object),
            asignacionCaja: expect.any(Object),
            autorizacion: expect.any(Object),
          })
        );
      });

      it('passing IMovimientoCaja should create a new form with FormGroup', () => {
        const formGroup = service.createMovimientoCajaFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            tipMovimiento: expect.any(Object),
            concepto: expect.any(Object),
            monto: expect.any(Object),
            tipMoneda: expect.any(Object),
            formPago: expect.any(Object),
            comprobante: expect.any(Object),
            fecMovimiento: expect.any(Object),
            version: expect.any(Object),
            indDel: expect.any(Object),
            fecCrea: expect.any(Object),
            usuCrea: expect.any(Object),
            ipCrea: expect.any(Object),
            fecModif: expect.any(Object),
            usuModif: expect.any(Object),
            ipModif: expect.any(Object),
            asignacionCaja: expect.any(Object),
            autorizacion: expect.any(Object),
          })
        );
      });
    });

    describe('getMovimientoCaja', () => {
      it('should return NewMovimientoCaja for default MovimientoCaja initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createMovimientoCajaFormGroup(sampleWithNewData);

        const movimientoCaja = service.getMovimientoCaja(formGroup) as any;

        expect(movimientoCaja).toMatchObject(sampleWithNewData);
      });

      it('should return NewMovimientoCaja for empty MovimientoCaja initial value', () => {
        const formGroup = service.createMovimientoCajaFormGroup();

        const movimientoCaja = service.getMovimientoCaja(formGroup) as any;

        expect(movimientoCaja).toMatchObject({});
      });

      it('should return IMovimientoCaja', () => {
        const formGroup = service.createMovimientoCajaFormGroup(sampleWithRequiredData);

        const movimientoCaja = service.getMovimientoCaja(formGroup) as any;

        expect(movimientoCaja).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IMovimientoCaja should not enable id FormControl', () => {
        const formGroup = service.createMovimientoCajaFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewMovimientoCaja should disable id FormControl', () => {
        const formGroup = service.createMovimientoCajaFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
