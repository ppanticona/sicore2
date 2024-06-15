import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../reg-venta.test-samples';

import { RegVentaFormService } from './reg-venta-form.service';

describe('RegVenta Form Service', () => {
  let service: RegVentaFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RegVentaFormService);
  });

  describe('Service methods', () => {
    describe('createRegVentaFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createRegVentaFormGroup();

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
            nroComp: expect.any(Object),
            consoDia: expect.any(Object),
            tipDocCli: expect.any(Object),
            nroDocCli: expect.any(Object),
            apeRazSocCli: expect.any(Object),
            valFacExpor: expect.any(Object),
            baseImpOperGrav: expect.any(Object),
            dsctoBaseImp: expect.any(Object),
            igvIpm: expect.any(Object),
            dsctoIgvIpm: expect.any(Object),
            impOpeExo: expect.any(Object),
            impTotOpeInafec: expect.any(Object),
            impSecCons: expect.any(Object),
            baseImpArroz: expect.any(Object),
            impVentArroz: expect.any(Object),
            otrosNoBaseImp: expect.any(Object),
            importeTotalComp: expect.any(Object),
            codMoneda: expect.any(Object),
            tipCambio: expect.any(Object),
            fecEmiModif: expect.any(Object),
            tipCompModif: expect.any(Object),
            nroSerieCompModif: expect.any(Object),
            nroCompModif: expect.any(Object),
            identContrato: expect.any(Object),
            errTipUno: expect.any(Object),
            indCompVancMp: expect.any(Object),
            estadoOperaCancMp: expect.any(Object),
            campoLibre: expect.any(Object),
            formPago: expect.any(Object),
            moneda: expect.any(Object),
            indDel: expect.any(Object),
            fecCrea: expect.any(Object),
            usuCrea: expect.any(Object),
            ipCrea: expect.any(Object),
            fecModif: expect.any(Object),
            usuModif: expect.any(Object),
            ipModif: expect.any(Object),
            orden: expect.any(Object),
          })
        );
      });

      it('passing IRegVenta should create a new form with FormGroup', () => {
        const formGroup = service.createRegVentaFormGroup(sampleWithRequiredData);

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
            nroComp: expect.any(Object),
            consoDia: expect.any(Object),
            tipDocCli: expect.any(Object),
            nroDocCli: expect.any(Object),
            apeRazSocCli: expect.any(Object),
            valFacExpor: expect.any(Object),
            baseImpOperGrav: expect.any(Object),
            dsctoBaseImp: expect.any(Object),
            igvIpm: expect.any(Object),
            dsctoIgvIpm: expect.any(Object),
            impOpeExo: expect.any(Object),
            impTotOpeInafec: expect.any(Object),
            impSecCons: expect.any(Object),
            baseImpArroz: expect.any(Object),
            impVentArroz: expect.any(Object),
            otrosNoBaseImp: expect.any(Object),
            importeTotalComp: expect.any(Object),
            codMoneda: expect.any(Object),
            tipCambio: expect.any(Object),
            fecEmiModif: expect.any(Object),
            tipCompModif: expect.any(Object),
            nroSerieCompModif: expect.any(Object),
            nroCompModif: expect.any(Object),
            identContrato: expect.any(Object),
            errTipUno: expect.any(Object),
            indCompVancMp: expect.any(Object),
            estadoOperaCancMp: expect.any(Object),
            campoLibre: expect.any(Object),
            formPago: expect.any(Object),
            moneda: expect.any(Object),
            indDel: expect.any(Object),
            fecCrea: expect.any(Object),
            usuCrea: expect.any(Object),
            ipCrea: expect.any(Object),
            fecModif: expect.any(Object),
            usuModif: expect.any(Object),
            ipModif: expect.any(Object),
            orden: expect.any(Object),
          })
        );
      });
    });

    describe('getRegVenta', () => {
      it('should return NewRegVenta for default RegVenta initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createRegVentaFormGroup(sampleWithNewData);

        const regVenta = service.getRegVenta(formGroup) as any;

        expect(regVenta).toMatchObject(sampleWithNewData);
      });

      it('should return NewRegVenta for empty RegVenta initial value', () => {
        const formGroup = service.createRegVentaFormGroup();

        const regVenta = service.getRegVenta(formGroup) as any;

        expect(regVenta).toMatchObject({});
      });

      it('should return IRegVenta', () => {
        const formGroup = service.createRegVentaFormGroup(sampleWithRequiredData);

        const regVenta = service.getRegVenta(formGroup) as any;

        expect(regVenta).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IRegVenta should not enable id FormControl', () => {
        const formGroup = service.createRegVentaFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewRegVenta should disable id FormControl', () => {
        const formGroup = service.createRegVentaFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
