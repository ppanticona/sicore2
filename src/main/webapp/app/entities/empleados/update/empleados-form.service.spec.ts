import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../empleados.test-samples';

import { EmpleadosFormService } from './empleados-form.service';

describe('Empleados Form Service', () => {
  let service: EmpleadosFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EmpleadosFormService);
  });

  describe('Service methods', () => {
    describe('createEmpleadosFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createEmpleadosFormGroup();

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
            fecIngreso: expect.any(Object),
            fecNac: expect.any(Object),
            imagen: expect.any(Object),
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

      it('passing IEmpleados should create a new form with FormGroup', () => {
        const formGroup = service.createEmpleadosFormGroup(sampleWithRequiredData);

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
            fecIngreso: expect.any(Object),
            fecNac: expect.any(Object),
            imagen: expect.any(Object),
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

    describe('getEmpleados', () => {
      it('should return NewEmpleados for default Empleados initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createEmpleadosFormGroup(sampleWithNewData);

        const empleados = service.getEmpleados(formGroup) as any;

        expect(empleados).toMatchObject(sampleWithNewData);
      });

      it('should return NewEmpleados for empty Empleados initial value', () => {
        const formGroup = service.createEmpleadosFormGroup();

        const empleados = service.getEmpleados(formGroup) as any;

        expect(empleados).toMatchObject({});
      });

      it('should return IEmpleados', () => {
        const formGroup = service.createEmpleadosFormGroup(sampleWithRequiredData);

        const empleados = service.getEmpleados(formGroup) as any;

        expect(empleados).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IEmpleados should not enable id FormControl', () => {
        const formGroup = service.createEmpleadosFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewEmpleados should disable id FormControl', () => {
        const formGroup = service.createEmpleadosFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
