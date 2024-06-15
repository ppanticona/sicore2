import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../reg-compras.test-samples';

import { RegComprasFormService } from './reg-compras-form.service';

describe('RegCompras Form Service', () => {
  let service: RegComprasFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RegComprasFormService);
  });

  describe('Service methods', () => {
    describe('createRegComprasFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createRegComprasFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            periodo: expect.any(Object),
            cuo: expect.any(Object),
            asientContab: expect.any(Object),
            fecEmiComp: expect.any(Object),
            fecVencComp: expect.any(Object),
            tipComp: expect.any(Object),
            nroSerieComp: expect.any(Object),
            anhoEmisionDua: expect.any(Object),
            nroComp: expect.any(Object),
            nroFinal: expect.any(Object),
            tipDocProv: expect.any(Object),
            nroDocProv: expect.any(Object),
            nomApeRazSocProv: expect.any(Object),
            baseImponible: expect.any(Object),
            montoIgv: expect.any(Object),
            baseImponible2: expect.any(Object),
            montoIgv2: expect.any(Object),
            baseImponible3: expect.any(Object),
            montoIgv3: expect.any(Object),
            montoNoGravado: expect.any(Object),
            montoIsc: expect.any(Object),
            impConsBols: expect.any(Object),
            otrosCargos: expect.any(Object),
            importeTotal: expect.any(Object),
            codMoneda: expect.any(Object),
            tipCambio: expect.any(Object),
            fecEmiModif: expect.any(Object),
            tipCompModif: expect.any(Object),
            nroSerieCompModif: expect.any(Object),
            codDuaRef: expect.any(Object),
            nroCompModif: expect.any(Object),
            fecEmiDetracc: expect.any(Object),
            nroConstDetracc: expect.any(Object),
            indCompSujRetenc: expect.any(Object),
            clasBienesyServicios: expect.any(Object),
            identContrato: expect.any(Object),
            errTipUno: expect.any(Object),
            errTipDos: expect.any(Object),
            errTipTres: expect.any(Object),
            errTipCuatro: expect.any(Object),
            indCompPagoMedPago: expect.any(Object),
            estado: expect.any(Object),
            campoLibre: expect.any(Object),
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

      it('passing IRegCompras should create a new form with FormGroup', () => {
        const formGroup = service.createRegComprasFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            periodo: expect.any(Object),
            cuo: expect.any(Object),
            asientContab: expect.any(Object),
            fecEmiComp: expect.any(Object),
            fecVencComp: expect.any(Object),
            tipComp: expect.any(Object),
            nroSerieComp: expect.any(Object),
            anhoEmisionDua: expect.any(Object),
            nroComp: expect.any(Object),
            nroFinal: expect.any(Object),
            tipDocProv: expect.any(Object),
            nroDocProv: expect.any(Object),
            nomApeRazSocProv: expect.any(Object),
            baseImponible: expect.any(Object),
            montoIgv: expect.any(Object),
            baseImponible2: expect.any(Object),
            montoIgv2: expect.any(Object),
            baseImponible3: expect.any(Object),
            montoIgv3: expect.any(Object),
            montoNoGravado: expect.any(Object),
            montoIsc: expect.any(Object),
            impConsBols: expect.any(Object),
            otrosCargos: expect.any(Object),
            importeTotal: expect.any(Object),
            codMoneda: expect.any(Object),
            tipCambio: expect.any(Object),
            fecEmiModif: expect.any(Object),
            tipCompModif: expect.any(Object),
            nroSerieCompModif: expect.any(Object),
            codDuaRef: expect.any(Object),
            nroCompModif: expect.any(Object),
            fecEmiDetracc: expect.any(Object),
            nroConstDetracc: expect.any(Object),
            indCompSujRetenc: expect.any(Object),
            clasBienesyServicios: expect.any(Object),
            identContrato: expect.any(Object),
            errTipUno: expect.any(Object),
            errTipDos: expect.any(Object),
            errTipTres: expect.any(Object),
            errTipCuatro: expect.any(Object),
            indCompPagoMedPago: expect.any(Object),
            estado: expect.any(Object),
            campoLibre: expect.any(Object),
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

    describe('getRegCompras', () => {
      it('should return NewRegCompras for default RegCompras initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createRegComprasFormGroup(sampleWithNewData);

        const regCompras = service.getRegCompras(formGroup) as any;

        expect(regCompras).toMatchObject(sampleWithNewData);
      });

      it('should return NewRegCompras for empty RegCompras initial value', () => {
        const formGroup = service.createRegComprasFormGroup();

        const regCompras = service.getRegCompras(formGroup) as any;

        expect(regCompras).toMatchObject({});
      });

      it('should return IRegCompras', () => {
        const formGroup = service.createRegComprasFormGroup(sampleWithRequiredData);

        const regCompras = service.getRegCompras(formGroup) as any;

        expect(regCompras).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IRegCompras should not enable id FormControl', () => {
        const formGroup = service.createRegComprasFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewRegCompras should disable id FormControl', () => {
        const formGroup = service.createRegComprasFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
