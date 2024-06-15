import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../proveedores.test-samples';

import { ProveedoresFormService } from './proveedores-form.service';

describe('Proveedores Form Service', () => {
  let service: ProveedoresFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProveedoresFormService);
  });

  describe('Service methods', () => {
    describe('createProveedoresFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createProveedoresFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            tipDoc: expect.any(Object),
            nroDoc: expect.any(Object),
            nombres: expect.any(Object),
            apellidos: expect.any(Object),
            categoria: expect.any(Object),
            tel1: expect.any(Object),
            tel2: expect.any(Object),
            correo: expect.any(Object),
            direccion: expect.any(Object),
            refDirecc: expect.any(Object),
            distrito: expect.any(Object),
            fecNac: expect.any(Object),
            userId: expect.any(Object),
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

      it('passing IProveedores should create a new form with FormGroup', () => {
        const formGroup = service.createProveedoresFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            tipDoc: expect.any(Object),
            nroDoc: expect.any(Object),
            nombres: expect.any(Object),
            apellidos: expect.any(Object),
            categoria: expect.any(Object),
            tel1: expect.any(Object),
            tel2: expect.any(Object),
            correo: expect.any(Object),
            direccion: expect.any(Object),
            refDirecc: expect.any(Object),
            distrito: expect.any(Object),
            fecNac: expect.any(Object),
            userId: expect.any(Object),
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

    describe('getProveedores', () => {
      it('should return NewProveedores for default Proveedores initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createProveedoresFormGroup(sampleWithNewData);

        const proveedores = service.getProveedores(formGroup) as any;

        expect(proveedores).toMatchObject(sampleWithNewData);
      });

      it('should return NewProveedores for empty Proveedores initial value', () => {
        const formGroup = service.createProveedoresFormGroup();

        const proveedores = service.getProveedores(formGroup) as any;

        expect(proveedores).toMatchObject({});
      });

      it('should return IProveedores', () => {
        const formGroup = service.createProveedoresFormGroup(sampleWithRequiredData);

        const proveedores = service.getProveedores(formGroup) as any;

        expect(proveedores).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IProveedores should not enable id FormControl', () => {
        const formGroup = service.createProveedoresFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewProveedores should disable id FormControl', () => {
        const formGroup = service.createProveedoresFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
