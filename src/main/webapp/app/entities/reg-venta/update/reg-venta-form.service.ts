import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IRegVenta, NewRegVenta } from '../reg-venta.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IRegVenta for edit and NewRegVentaFormGroupInput for create.
 */
type RegVentaFormGroupInput = IRegVenta | PartialWithRequiredKeyOf<NewRegVenta>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IRegVenta | NewRegVenta> = Omit<T, 'fecEmiComp' | 'fecVencComp' | 'fecEmiModif' | 'fecCrea' | 'fecModif'> & {
  fecEmiComp?: string | null;
  fecVencComp?: string | null;
  fecEmiModif?: string | null;
  fecCrea?: string | null;
  fecModif?: string | null;
};

type RegVentaFormRawValue = FormValueOf<IRegVenta>;

type NewRegVentaFormRawValue = FormValueOf<NewRegVenta>;

type RegVentaFormDefaults = Pick<NewRegVenta, 'id' | 'fecEmiComp' | 'fecVencComp' | 'fecEmiModif' | 'indDel' | 'fecCrea' | 'fecModif'>;

type RegVentaFormGroupContent = {
  id: FormControl<RegVentaFormRawValue['id'] | NewRegVenta['id']>;
  periodo: FormControl<RegVentaFormRawValue['periodo']>;
  cuo: FormControl<RegVentaFormRawValue['cuo']>;
  asientContab: FormControl<RegVentaFormRawValue['asientContab']>;
  fecEmiComp: FormControl<RegVentaFormRawValue['fecEmiComp']>;
  fecVencComp: FormControl<RegVentaFormRawValue['fecVencComp']>;
  tipComp: FormControl<RegVentaFormRawValue['tipComp']>;
  nroSerieComp: FormControl<RegVentaFormRawValue['nroSerieComp']>;
  nroComp: FormControl<RegVentaFormRawValue['nroComp']>;
  consoDia: FormControl<RegVentaFormRawValue['consoDia']>;
  tipDocCli: FormControl<RegVentaFormRawValue['tipDocCli']>;
  nroDocCli: FormControl<RegVentaFormRawValue['nroDocCli']>;
  apeRazSocCli: FormControl<RegVentaFormRawValue['apeRazSocCli']>;
  valFacExpor: FormControl<RegVentaFormRawValue['valFacExpor']>;
  baseImpOperGrav: FormControl<RegVentaFormRawValue['baseImpOperGrav']>;
  dsctoBaseImp: FormControl<RegVentaFormRawValue['dsctoBaseImp']>;
  igvIpm: FormControl<RegVentaFormRawValue['igvIpm']>;
  dsctoIgvIpm: FormControl<RegVentaFormRawValue['dsctoIgvIpm']>;
  impOpeExo: FormControl<RegVentaFormRawValue['impOpeExo']>;
  impTotOpeInafec: FormControl<RegVentaFormRawValue['impTotOpeInafec']>;
  impSecCons: FormControl<RegVentaFormRawValue['impSecCons']>;
  baseImpArroz: FormControl<RegVentaFormRawValue['baseImpArroz']>;
  impVentArroz: FormControl<RegVentaFormRawValue['impVentArroz']>;
  otrosNoBaseImp: FormControl<RegVentaFormRawValue['otrosNoBaseImp']>;
  importeTotalComp: FormControl<RegVentaFormRawValue['importeTotalComp']>;
  codMoneda: FormControl<RegVentaFormRawValue['codMoneda']>;
  tipCambio: FormControl<RegVentaFormRawValue['tipCambio']>;
  fecEmiModif: FormControl<RegVentaFormRawValue['fecEmiModif']>;
  tipCompModif: FormControl<RegVentaFormRawValue['tipCompModif']>;
  nroSerieCompModif: FormControl<RegVentaFormRawValue['nroSerieCompModif']>;
  nroCompModif: FormControl<RegVentaFormRawValue['nroCompModif']>;
  identContrato: FormControl<RegVentaFormRawValue['identContrato']>;
  errTipUno: FormControl<RegVentaFormRawValue['errTipUno']>;
  indCompVancMp: FormControl<RegVentaFormRawValue['indCompVancMp']>;
  estadoOperaCancMp: FormControl<RegVentaFormRawValue['estadoOperaCancMp']>;
  campoLibre: FormControl<RegVentaFormRawValue['campoLibre']>;
  formPago: FormControl<RegVentaFormRawValue['formPago']>;
  moneda: FormControl<RegVentaFormRawValue['moneda']>;
  indDel: FormControl<RegVentaFormRawValue['indDel']>;
  fecCrea: FormControl<RegVentaFormRawValue['fecCrea']>;
  usuCrea: FormControl<RegVentaFormRawValue['usuCrea']>;
  ipCrea: FormControl<RegVentaFormRawValue['ipCrea']>;
  fecModif: FormControl<RegVentaFormRawValue['fecModif']>;
  usuModif: FormControl<RegVentaFormRawValue['usuModif']>;
  ipModif: FormControl<RegVentaFormRawValue['ipModif']>;
  orden: FormControl<RegVentaFormRawValue['orden']>;
};

export type RegVentaFormGroup = FormGroup<RegVentaFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class RegVentaFormService {
  createRegVentaFormGroup(regVenta: RegVentaFormGroupInput = { id: null }): RegVentaFormGroup {
    const regVentaRawValue = this.convertRegVentaToRegVentaRawValue({
      ...this.getFormDefaults(),
      ...regVenta,
    });
    return new FormGroup<RegVentaFormGroupContent>({
      id: new FormControl(
        { value: regVentaRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      periodo: new FormControl(regVentaRawValue.periodo, {
        validators: [Validators.required],
      }),
      cuo: new FormControl(regVentaRawValue.cuo, {
        validators: [Validators.required],
      }),
      asientContab: new FormControl(regVentaRawValue.asientContab),
      fecEmiComp: new FormControl(regVentaRawValue.fecEmiComp, {
        validators: [Validators.required],
      }),
      fecVencComp: new FormControl(regVentaRawValue.fecVencComp),
      tipComp: new FormControl(regVentaRawValue.tipComp, {
        validators: [Validators.required],
      }),
      nroSerieComp: new FormControl(regVentaRawValue.nroSerieComp, {
        validators: [Validators.required],
      }),
      nroComp: new FormControl(regVentaRawValue.nroComp, {
        validators: [Validators.required],
      }),
      consoDia: new FormControl(regVentaRawValue.consoDia),
      tipDocCli: new FormControl(regVentaRawValue.tipDocCli, {
        validators: [Validators.required],
      }),
      nroDocCli: new FormControl(regVentaRawValue.nroDocCli, {
        validators: [Validators.required],
      }),
      apeRazSocCli: new FormControl(regVentaRawValue.apeRazSocCli, {
        validators: [Validators.required],
      }),
      valFacExpor: new FormControl(regVentaRawValue.valFacExpor),
      baseImpOperGrav: new FormControl(regVentaRawValue.baseImpOperGrav, {
        validators: [Validators.required],
      }),
      dsctoBaseImp: new FormControl(regVentaRawValue.dsctoBaseImp, {
        validators: [Validators.required],
      }),
      igvIpm: new FormControl(regVentaRawValue.igvIpm, {
        validators: [Validators.required],
      }),
      dsctoIgvIpm: new FormControl(regVentaRawValue.dsctoIgvIpm, {
        validators: [Validators.required],
      }),
      impOpeExo: new FormControl(regVentaRawValue.impOpeExo, {
        validators: [Validators.required],
      }),
      impTotOpeInafec: new FormControl(regVentaRawValue.impTotOpeInafec, {
        validators: [Validators.required],
      }),
      impSecCons: new FormControl(regVentaRawValue.impSecCons),
      baseImpArroz: new FormControl(regVentaRawValue.baseImpArroz),
      impVentArroz: new FormControl(regVentaRawValue.impVentArroz),
      otrosNoBaseImp: new FormControl(regVentaRawValue.otrosNoBaseImp),
      importeTotalComp: new FormControl(regVentaRawValue.importeTotalComp, {
        validators: [Validators.required],
      }),
      codMoneda: new FormControl(regVentaRawValue.codMoneda, {
        validators: [Validators.required],
      }),
      tipCambio: new FormControl(regVentaRawValue.tipCambio, {
        validators: [Validators.required],
      }),
      fecEmiModif: new FormControl(regVentaRawValue.fecEmiModif),
      tipCompModif: new FormControl(regVentaRawValue.tipCompModif),
      nroSerieCompModif: new FormControl(regVentaRawValue.nroSerieCompModif),
      nroCompModif: new FormControl(regVentaRawValue.nroCompModif),
      identContrato: new FormControl(regVentaRawValue.identContrato),
      errTipUno: new FormControl(regVentaRawValue.errTipUno),
      indCompVancMp: new FormControl(regVentaRawValue.indCompVancMp),
      estadoOperaCancMp: new FormControl(regVentaRawValue.estadoOperaCancMp),
      campoLibre: new FormControl(regVentaRawValue.campoLibre),
      formPago: new FormControl(regVentaRawValue.formPago),
      moneda: new FormControl(regVentaRawValue.moneda),
      indDel: new FormControl(regVentaRawValue.indDel, {
        validators: [Validators.required],
      }),
      fecCrea: new FormControl(regVentaRawValue.fecCrea, {
        validators: [Validators.required],
      }),
      usuCrea: new FormControl(regVentaRawValue.usuCrea, {
        validators: [Validators.required],
      }),
      ipCrea: new FormControl(regVentaRawValue.ipCrea, {
        validators: [Validators.required],
      }),
      fecModif: new FormControl(regVentaRawValue.fecModif),
      usuModif: new FormControl(regVentaRawValue.usuModif),
      ipModif: new FormControl(regVentaRawValue.ipModif),
      orden: new FormControl(regVentaRawValue.orden),
    });
  }

  getRegVenta(form: RegVentaFormGroup): IRegVenta | NewRegVenta {
    return this.convertRegVentaRawValueToRegVenta(form.getRawValue() as RegVentaFormRawValue | NewRegVentaFormRawValue);
  }

  resetForm(form: RegVentaFormGroup, regVenta: RegVentaFormGroupInput): void {
    const regVentaRawValue = this.convertRegVentaToRegVentaRawValue({ ...this.getFormDefaults(), ...regVenta });
    form.reset(
      {
        ...regVentaRawValue,
        id: { value: regVentaRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): RegVentaFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      fecEmiComp: currentTime,
      fecVencComp: currentTime,
      fecEmiModif: currentTime,
      indDel: false,
      fecCrea: currentTime,
      fecModif: currentTime,
    };
  }

  private convertRegVentaRawValueToRegVenta(rawRegVenta: RegVentaFormRawValue | NewRegVentaFormRawValue): IRegVenta | NewRegVenta {
    return {
      ...rawRegVenta,
      fecEmiComp: dayjs(rawRegVenta.fecEmiComp, DATE_TIME_FORMAT),
      fecVencComp: dayjs(rawRegVenta.fecVencComp, DATE_TIME_FORMAT),
      fecEmiModif: dayjs(rawRegVenta.fecEmiModif, DATE_TIME_FORMAT),
      fecCrea: dayjs(rawRegVenta.fecCrea, DATE_TIME_FORMAT),
      fecModif: dayjs(rawRegVenta.fecModif, DATE_TIME_FORMAT),
    };
  }

  private convertRegVentaToRegVentaRawValue(
    regVenta: IRegVenta | (Partial<NewRegVenta> & RegVentaFormDefaults)
  ): RegVentaFormRawValue | PartialWithRequiredKeyOf<NewRegVentaFormRawValue> {
    return {
      ...regVenta,
      fecEmiComp: regVenta.fecEmiComp ? regVenta.fecEmiComp.format(DATE_TIME_FORMAT) : undefined,
      fecVencComp: regVenta.fecVencComp ? regVenta.fecVencComp.format(DATE_TIME_FORMAT) : undefined,
      fecEmiModif: regVenta.fecEmiModif ? regVenta.fecEmiModif.format(DATE_TIME_FORMAT) : undefined,
      fecCrea: regVenta.fecCrea ? regVenta.fecCrea.format(DATE_TIME_FORMAT) : undefined,
      fecModif: regVenta.fecModif ? regVenta.fecModif.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
