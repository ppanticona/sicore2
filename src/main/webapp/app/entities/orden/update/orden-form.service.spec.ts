import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../orden.test-samples';

import { OrdenFormService } from './orden-form.service';

describe('Orden Form Service', () => {
  let service: OrdenFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OrdenFormService);
  });

  describe('Service methods', () => {
    describe('createOrdenFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createOrdenFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            numOrden: expect.any(Object),
            observacion: expect.any(Object),
            fecIngreso: expect.any(Object),
            fecSalida: expect.any(Object),
            codCanal: expect.any(Object),
            tipOrden: expect.any(Object),
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

      it('passing IOrden should create a new form with FormGroup', () => {
        const formGroup = service.createOrdenFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            numOrden: expect.any(Object),
            observacion: expect.any(Object),
            fecIngreso: expect.any(Object),
            fecSalida: expect.any(Object),
            codCanal: expect.any(Object),
            tipOrden: expect.any(Object),
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

    describe('getOrden', () => {
      it('should return NewOrden for default Orden initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createOrdenFormGroup(sampleWithNewData);

        const orden = service.getOrden(formGroup) as any;

        expect(orden).toMatchObject(sampleWithNewData);
      });

      it('should return NewOrden for empty Orden initial value', () => {
        const formGroup = service.createOrdenFormGroup();

        const orden = service.getOrden(formGroup) as any;

        expect(orden).toMatchObject({});
      });

      it('should return IOrden', () => {
        const formGroup = service.createOrdenFormGroup(sampleWithRequiredData);

        const orden = service.getOrden(formGroup) as any;

        expect(orden).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IOrden should not enable id FormControl', () => {
        const formGroup = service.createOrdenFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewOrden should disable id FormControl', () => {
        const formGroup = service.createOrdenFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
