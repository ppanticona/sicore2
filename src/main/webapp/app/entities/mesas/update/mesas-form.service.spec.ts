import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../mesas.test-samples';

import { MesasFormService } from './mesas-form.service';

describe('Mesas Form Service', () => {
  let service: MesasFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MesasFormService);
  });

  describe('Service methods', () => {
    describe('createMesasFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createMesasFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            seqMesa: expect.any(Object),
            nroMesa: expect.any(Object),
            codGrupo: expect.any(Object),
            categoria: expect.any(Object),
            capacidad: expect.any(Object),
            indMesaJunta: expect.any(Object),
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
            sede: expect.any(Object),
            user: expect.any(Object),
          })
        );
      });

      it('passing IMesas should create a new form with FormGroup', () => {
        const formGroup = service.createMesasFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            seqMesa: expect.any(Object),
            nroMesa: expect.any(Object),
            codGrupo: expect.any(Object),
            categoria: expect.any(Object),
            capacidad: expect.any(Object),
            indMesaJunta: expect.any(Object),
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
            sede: expect.any(Object),
            user: expect.any(Object),
          })
        );
      });
    });

    describe('getMesas', () => {
      it('should return NewMesas for default Mesas initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createMesasFormGroup(sampleWithNewData);

        const mesas = service.getMesas(formGroup) as any;

        expect(mesas).toMatchObject(sampleWithNewData);
      });

      it('should return NewMesas for empty Mesas initial value', () => {
        const formGroup = service.createMesasFormGroup();

        const mesas = service.getMesas(formGroup) as any;

        expect(mesas).toMatchObject({});
      });

      it('should return IMesas', () => {
        const formGroup = service.createMesasFormGroup(sampleWithRequiredData);

        const mesas = service.getMesas(formGroup) as any;

        expect(mesas).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IMesas should not enable id FormControl', () => {
        const formGroup = service.createMesasFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewMesas should disable id FormControl', () => {
        const formGroup = service.createMesasFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
