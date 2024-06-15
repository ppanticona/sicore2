import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../clientes.test-samples';

import { ClientesFormService } from './clientes-form.service';

describe('Clientes Form Service', () => {
  let service: ClientesFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ClientesFormService);
  });

  describe('Service methods', () => {
    describe('createClientesFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createClientesFormGroup();

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

      it('passing IClientes should create a new form with FormGroup', () => {
        const formGroup = service.createClientesFormGroup(sampleWithRequiredData);

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

    describe('getClientes', () => {
      it('should return NewClientes for default Clientes initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createClientesFormGroup(sampleWithNewData);

        const clientes = service.getClientes(formGroup) as any;

        expect(clientes).toMatchObject(sampleWithNewData);
      });

      it('should return NewClientes for empty Clientes initial value', () => {
        const formGroup = service.createClientesFormGroup();

        const clientes = service.getClientes(formGroup) as any;

        expect(clientes).toMatchObject({});
      });

      it('should return IClientes', () => {
        const formGroup = service.createClientesFormGroup(sampleWithRequiredData);

        const clientes = service.getClientes(formGroup) as any;

        expect(clientes).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IClientes should not enable id FormControl', () => {
        const formGroup = service.createClientesFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewClientes should disable id FormControl', () => {
        const formGroup = service.createClientesFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
