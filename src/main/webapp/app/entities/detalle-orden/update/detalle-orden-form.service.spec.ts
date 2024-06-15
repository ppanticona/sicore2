import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../detalle-orden.test-samples';

import { DetalleOrdenFormService } from './detalle-orden-form.service';

describe('DetalleOrden Form Service', () => {
  let service: DetalleOrdenFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DetalleOrdenFormService);
  });

  describe('Service methods', () => {
    describe('createDetalleOrdenFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createDetalleOrdenFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            observacion: expect.any(Object),
            monto: expect.any(Object),
            indPagado: expect.any(Object),
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
            promocion: expect.any(Object),
            autorizacion: expect.any(Object),
            producto: expect.any(Object),
          })
        );
      });

      it('passing IDetalleOrden should create a new form with FormGroup', () => {
        const formGroup = service.createDetalleOrdenFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            observacion: expect.any(Object),
            monto: expect.any(Object),
            indPagado: expect.any(Object),
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
            promocion: expect.any(Object),
            autorizacion: expect.any(Object),
            producto: expect.any(Object),
          })
        );
      });
    });

    describe('getDetalleOrden', () => {
      it('should return NewDetalleOrden for default DetalleOrden initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createDetalleOrdenFormGroup(sampleWithNewData);

        const detalleOrden = service.getDetalleOrden(formGroup) as any;

        expect(detalleOrden).toMatchObject(sampleWithNewData);
      });

      it('should return NewDetalleOrden for empty DetalleOrden initial value', () => {
        const formGroup = service.createDetalleOrdenFormGroup();

        const detalleOrden = service.getDetalleOrden(formGroup) as any;

        expect(detalleOrden).toMatchObject({});
      });

      it('should return IDetalleOrden', () => {
        const formGroup = service.createDetalleOrdenFormGroup(sampleWithRequiredData);

        const detalleOrden = service.getDetalleOrden(formGroup) as any;

        expect(detalleOrden).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IDetalleOrden should not enable id FormControl', () => {
        const formGroup = service.createDetalleOrdenFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewDetalleOrden should disable id FormControl', () => {
        const formGroup = service.createDetalleOrdenFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
