import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../detalle-precuenta.test-samples';

import { DetallePrecuentaFormService } from './detalle-precuenta-form.service';

describe('DetallePrecuenta Form Service', () => {
  let service: DetallePrecuentaFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DetallePrecuentaFormService);
  });

  describe('Service methods', () => {
    describe('createDetallePrecuentaFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createDetallePrecuentaFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            correlativo: expect.any(Object),
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
            autorizacion: expect.any(Object),
            regVenta: expect.any(Object),
            precuenta: expect.any(Object),
            detalleOrden: expect.any(Object),
          })
        );
      });

      it('passing IDetallePrecuenta should create a new form with FormGroup', () => {
        const formGroup = service.createDetallePrecuentaFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            correlativo: expect.any(Object),
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
            autorizacion: expect.any(Object),
            regVenta: expect.any(Object),
            precuenta: expect.any(Object),
            detalleOrden: expect.any(Object),
          })
        );
      });
    });

    describe('getDetallePrecuenta', () => {
      it('should return NewDetallePrecuenta for default DetallePrecuenta initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createDetallePrecuentaFormGroup(sampleWithNewData);

        const detallePrecuenta = service.getDetallePrecuenta(formGroup) as any;

        expect(detallePrecuenta).toMatchObject(sampleWithNewData);
      });

      it('should return NewDetallePrecuenta for empty DetallePrecuenta initial value', () => {
        const formGroup = service.createDetallePrecuentaFormGroup();

        const detallePrecuenta = service.getDetallePrecuenta(formGroup) as any;

        expect(detallePrecuenta).toMatchObject({});
      });

      it('should return IDetallePrecuenta', () => {
        const formGroup = service.createDetallePrecuentaFormGroup(sampleWithRequiredData);

        const detallePrecuenta = service.getDetallePrecuenta(formGroup) as any;

        expect(detallePrecuenta).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IDetallePrecuenta should not enable id FormControl', () => {
        const formGroup = service.createDetallePrecuentaFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewDetallePrecuenta should disable id FormControl', () => {
        const formGroup = service.createDetallePrecuentaFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
