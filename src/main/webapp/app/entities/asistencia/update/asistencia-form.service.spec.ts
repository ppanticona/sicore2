import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../asistencia.test-samples';

import { AsistenciaFormService } from './asistencia-form.service';

describe('Asistencia Form Service', () => {
  let service: AsistenciaFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AsistenciaFormService);
  });

  describe('Service methods', () => {
    describe('createAsistenciaFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createAsistenciaFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            tipAsistente: expect.any(Object),
            resultado: expect.any(Object),
            anoAsistencia: expect.any(Object),
            mesAsistencia: expect.any(Object),
            diaAsistencia: expect.any(Object),
            observacion: expect.any(Object),
            comentarios: expect.any(Object),
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
          })
        );
      });

      it('passing IAsistencia should create a new form with FormGroup', () => {
        const formGroup = service.createAsistenciaFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            tipAsistente: expect.any(Object),
            resultado: expect.any(Object),
            anoAsistencia: expect.any(Object),
            mesAsistencia: expect.any(Object),
            diaAsistencia: expect.any(Object),
            observacion: expect.any(Object),
            comentarios: expect.any(Object),
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
          })
        );
      });
    });

    describe('getAsistencia', () => {
      it('should return NewAsistencia for default Asistencia initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createAsistenciaFormGroup(sampleWithNewData);

        const asistencia = service.getAsistencia(formGroup) as any;

        expect(asistencia).toMatchObject(sampleWithNewData);
      });

      it('should return NewAsistencia for empty Asistencia initial value', () => {
        const formGroup = service.createAsistenciaFormGroup();

        const asistencia = service.getAsistencia(formGroup) as any;

        expect(asistencia).toMatchObject({});
      });

      it('should return IAsistencia', () => {
        const formGroup = service.createAsistenciaFormGroup(sampleWithRequiredData);

        const asistencia = service.getAsistencia(formGroup) as any;

        expect(asistencia).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IAsistencia should not enable id FormControl', () => {
        const formGroup = service.createAsistenciaFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewAsistencia should disable id FormControl', () => {
        const formGroup = service.createAsistenciaFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
