import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../sede.test-samples';

import { SedeFormService } from './sede-form.service';

describe('Sede Form Service', () => {
  let service: SedeFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SedeFormService);
  });

  describe('Service methods', () => {
    describe('createSedeFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createSedeFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            codSede: expect.any(Object),
            descripcion: expect.any(Object),
            categoria: expect.any(Object),
            tel1: expect.any(Object),
            tel2: expect.any(Object),
            correo: expect.any(Object),
            direccion: expect.any(Object),
            refDirecc: expect.any(Object),
            distrito: expect.any(Object),
            fecAper: expect.any(Object),
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

      it('passing ISede should create a new form with FormGroup', () => {
        const formGroup = service.createSedeFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            codSede: expect.any(Object),
            descripcion: expect.any(Object),
            categoria: expect.any(Object),
            tel1: expect.any(Object),
            tel2: expect.any(Object),
            correo: expect.any(Object),
            direccion: expect.any(Object),
            refDirecc: expect.any(Object),
            distrito: expect.any(Object),
            fecAper: expect.any(Object),
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

    describe('getSede', () => {
      it('should return NewSede for default Sede initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createSedeFormGroup(sampleWithNewData);

        const sede = service.getSede(formGroup) as any;

        expect(sede).toMatchObject(sampleWithNewData);
      });

      it('should return NewSede for empty Sede initial value', () => {
        const formGroup = service.createSedeFormGroup();

        const sede = service.getSede(formGroup) as any;

        expect(sede).toMatchObject({});
      });

      it('should return ISede', () => {
        const formGroup = service.createSedeFormGroup(sampleWithRequiredData);

        const sede = service.getSede(formGroup) as any;

        expect(sede).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ISede should not enable id FormControl', () => {
        const formGroup = service.createSedeFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewSede should disable id FormControl', () => {
        const formGroup = service.createSedeFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
