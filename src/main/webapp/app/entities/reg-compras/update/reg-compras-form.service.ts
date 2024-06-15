import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IRegCompras, NewRegCompras } from '../reg-compras.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IRegCompras for edit and NewRegComprasFormGroupInput for create.
 */
type RegComprasFormGroupInput = IRegCompras | PartialWithRequiredKeyOf<NewRegCompras>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IRegCompras | NewRegCompras> = Omit<
  T,
  'fecEmiComp' | 'fecVencComp' | 'fecEmiModif' | 'fecEmiDetracc' | 'fecCrea' | 'fecModif'
> & {
  fecEmiComp?: string | null;
  fecVencComp?: string | null;
  fecEmiModif?: string | null;
  fecEmiDetracc?: string | null;
  fecCrea?: string | null;
  fecModif?: string | null;
};

type RegComprasFormRawValue = FormValueOf<IRegCompras>;

type NewRegComprasFormRawValue = FormValueOf<NewRegCompras>;

type RegComprasFormDefaults = Pick<
  NewRegCompras,
  'id' | 'fecEmiComp' | 'fecVencComp' | 'fecEmiModif' | 'fecEmiDetracc' | 'indDel' | 'fecCrea' | 'fecModif'
>;

type RegComprasFormGroupContent = {
  id: FormControl<RegComprasFormRawValue['id'] | NewRegCompras['id']>;
  periodo: FormControl<RegComprasFormRawValue['periodo']>;
  cuo: FormControl<RegComprasFormRawValue['cuo']>;
  asientContab: FormControl<RegComprasFormRawValue['asientContab']>;
  fecEmiComp: FormControl<RegComprasFormRawValue['fecEmiComp']>;
  fecVencComp: FormControl<RegComprasFormRawValue['fecVencComp']>;
  tipComp: FormControl<RegComprasFormRawValue['tipComp']>;
  nroSerieComp: FormControl<RegComprasFormRawValue['nroSerieComp']>;
  anhoEmisionDua: FormControl<RegComprasFormRawValue['anhoEmisionDua']>;
  nroComp: FormControl<RegComprasFormRawValue['nroComp']>;
  nroFinal: FormControl<RegComprasFormRawValue['nroFinal']>;
  tipDocProv: FormControl<RegComprasFormRawValue['tipDocProv']>;
  nroDocProv: FormControl<RegComprasFormRawValue['nroDocProv']>;
  nomApeRazSocProv: FormControl<RegComprasFormRawValue['nomApeRazSocProv']>;
  baseImponible: FormControl<RegComprasFormRawValue['baseImponible']>;
  montoIgv: FormControl<RegComprasFormRawValue['montoIgv']>;
  baseImponible2: FormControl<RegComprasFormRawValue['baseImponible2']>;
  montoIgv2: FormControl<RegComprasFormRawValue['montoIgv2']>;
  baseImponible3: FormControl<RegComprasFormRawValue['baseImponible3']>;
  montoIgv3: FormControl<RegComprasFormRawValue['montoIgv3']>;
  montoNoGravado: FormControl<RegComprasFormRawValue['montoNoGravado']>;
  montoIsc: FormControl<RegComprasFormRawValue['montoIsc']>;
  impConsBols: FormControl<RegComprasFormRawValue['impConsBols']>;
  otrosCargos: FormControl<RegComprasFormRawValue['otrosCargos']>;
  importeTotal: FormControl<RegComprasFormRawValue['importeTotal']>;
  codMoneda: FormControl<RegComprasFormRawValue['codMoneda']>;
  tipCambio: FormControl<RegComprasFormRawValue['tipCambio']>;
  fecEmiModif: FormControl<RegComprasFormRawValue['fecEmiModif']>;
  tipCompModif: FormControl<RegComprasFormRawValue['tipCompModif']>;
  nroSerieCompModif: FormControl<RegComprasFormRawValue['nroSerieCompModif']>;
  codDuaRef: FormControl<RegComprasFormRawValue['codDuaRef']>;
  nroCompModif: FormControl<RegComprasFormRawValue['nroCompModif']>;
  fecEmiDetracc: FormControl<RegComprasFormRawValue['fecEmiDetracc']>;
  nroConstDetracc: FormControl<RegComprasFormRawValue['nroConstDetracc']>;
  indCompSujRetenc: FormControl<RegComprasFormRawValue['indCompSujRetenc']>;
  clasBienesyServicios: FormControl<RegComprasFormRawValue['clasBienesyServicios']>;
  identContrato: FormControl<RegComprasFormRawValue['identContrato']>;
  errTipUno: FormControl<RegComprasFormRawValue['errTipUno']>;
  errTipDos: FormControl<RegComprasFormRawValue['errTipDos']>;
  errTipTres: FormControl<RegComprasFormRawValue['errTipTres']>;
  errTipCuatro: FormControl<RegComprasFormRawValue['errTipCuatro']>;
  indCompPagoMedPago: FormControl<RegComprasFormRawValue['indCompPagoMedPago']>;
  estado: FormControl<RegComprasFormRawValue['estado']>;
  campoLibre: FormControl<RegComprasFormRawValue['campoLibre']>;
  indDel: FormControl<RegComprasFormRawValue['indDel']>;
  fecCrea: FormControl<RegComprasFormRawValue['fecCrea']>;
  usuCrea: FormControl<RegComprasFormRawValue['usuCrea']>;
  ipCrea: FormControl<RegComprasFormRawValue['ipCrea']>;
  fecModif: FormControl<RegComprasFormRawValue['fecModif']>;
  usuModif: FormControl<RegComprasFormRawValue['usuModif']>;
  ipModif: FormControl<RegComprasFormRawValue['ipModif']>;
};

export type RegComprasFormGroup = FormGroup<RegComprasFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class RegComprasFormService {
  createRegComprasFormGroup(regCompras: RegComprasFormGroupInput = { id: null }): RegComprasFormGroup {
    const regComprasRawValue = this.convertRegComprasToRegComprasRawValue({
      ...this.getFormDefaults(),
      ...regCompras,
    });
    return new FormGroup<RegComprasFormGroupContent>({
      id: new FormControl(
        { value: regComprasRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      periodo: new FormControl(regComprasRawValue.periodo, {
        validators: [Validators.required],
      }),
      cuo: new FormControl(regComprasRawValue.cuo, {
        validators: [Validators.required],
      }),
      asientContab: new FormControl(regComprasRawValue.asientContab),
      fecEmiComp: new FormControl(regComprasRawValue.fecEmiComp, {
        validators: [Validators.required],
      }),
      fecVencComp: new FormControl(regComprasRawValue.fecVencComp),
      tipComp: new FormControl(regComprasRawValue.tipComp, {
        validators: [Validators.required],
      }),
      nroSerieComp: new FormControl(regComprasRawValue.nroSerieComp, {
        validators: [Validators.required],
      }),
      anhoEmisionDua: new FormControl(regComprasRawValue.anhoEmisionDua),
      nroComp: new FormControl(regComprasRawValue.nroComp, {
        validators: [Validators.required],
      }),
      nroFinal: new FormControl(regComprasRawValue.nroFinal),
      tipDocProv: new FormControl(regComprasRawValue.tipDocProv, {
        validators: [Validators.required],
      }),
      nroDocProv: new FormControl(regComprasRawValue.nroDocProv, {
        validators: [Validators.required],
      }),
      nomApeRazSocProv: new FormControl(regComprasRawValue.nomApeRazSocProv, {
        validators: [Validators.required],
      }),
      baseImponible: new FormControl(regComprasRawValue.baseImponible, {
        validators: [Validators.required],
      }),
      montoIgv: new FormControl(regComprasRawValue.montoIgv, {
        validators: [Validators.required],
      }),
      baseImponible2: new FormControl(regComprasRawValue.baseImponible2, {
        validators: [Validators.required],
      }),
      montoIgv2: new FormControl(regComprasRawValue.montoIgv2, {
        validators: [Validators.required],
      }),
      baseImponible3: new FormControl(regComprasRawValue.baseImponible3),
      montoIgv3: new FormControl(regComprasRawValue.montoIgv3, {
        validators: [Validators.required],
      }),
      montoNoGravado: new FormControl(regComprasRawValue.montoNoGravado),
      montoIsc: new FormControl(regComprasRawValue.montoIsc),
      impConsBols: new FormControl(regComprasRawValue.impConsBols),
      otrosCargos: new FormControl(regComprasRawValue.otrosCargos),
      importeTotal: new FormControl(regComprasRawValue.importeTotal),
      codMoneda: new FormControl(regComprasRawValue.codMoneda),
      tipCambio: new FormControl(regComprasRawValue.tipCambio),
      fecEmiModif: new FormControl(regComprasRawValue.fecEmiModif),
      tipCompModif: new FormControl(regComprasRawValue.tipCompModif),
      nroSerieCompModif: new FormControl(regComprasRawValue.nroSerieCompModif),
      codDuaRef: new FormControl(regComprasRawValue.codDuaRef),
      nroCompModif: new FormControl(regComprasRawValue.nroCompModif),
      fecEmiDetracc: new FormControl(regComprasRawValue.fecEmiDetracc),
      nroConstDetracc: new FormControl(regComprasRawValue.nroConstDetracc),
      indCompSujRetenc: new FormControl(regComprasRawValue.indCompSujRetenc),
      clasBienesyServicios: new FormControl(regComprasRawValue.clasBienesyServicios),
      identContrato: new FormControl(regComprasRawValue.identContrato),
      errTipUno: new FormControl(regComprasRawValue.errTipUno),
      errTipDos: new FormControl(regComprasRawValue.errTipDos),
      errTipTres: new FormControl(regComprasRawValue.errTipTres),
      errTipCuatro: new FormControl(regComprasRawValue.errTipCuatro),
      indCompPagoMedPago: new FormControl(regComprasRawValue.indCompPagoMedPago),
      estado: new FormControl(regComprasRawValue.estado),
      campoLibre: new FormControl(regComprasRawValue.campoLibre),
      indDel: new FormControl(regComprasRawValue.indDel, {
        validators: [Validators.required],
      }),
      fecCrea: new FormControl(regComprasRawValue.fecCrea, {
        validators: [Validators.required],
      }),
      usuCrea: new FormControl(regComprasRawValue.usuCrea, {
        validators: [Validators.required],
      }),
      ipCrea: new FormControl(regComprasRawValue.ipCrea, {
        validators: [Validators.required],
      }),
      fecModif: new FormControl(regComprasRawValue.fecModif),
      usuModif: new FormControl(regComprasRawValue.usuModif),
      ipModif: new FormControl(regComprasRawValue.ipModif),
    });
  }

  getRegCompras(form: RegComprasFormGroup): IRegCompras | NewRegCompras {
    return this.convertRegComprasRawValueToRegCompras(form.getRawValue() as RegComprasFormRawValue | NewRegComprasFormRawValue);
  }

  resetForm(form: RegComprasFormGroup, regCompras: RegComprasFormGroupInput): void {
    const regComprasRawValue = this.convertRegComprasToRegComprasRawValue({ ...this.getFormDefaults(), ...regCompras });
    form.reset(
      {
        ...regComprasRawValue,
        id: { value: regComprasRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): RegComprasFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      fecEmiComp: currentTime,
      fecVencComp: currentTime,
      fecEmiModif: currentTime,
      fecEmiDetracc: currentTime,
      indDel: false,
      fecCrea: currentTime,
      fecModif: currentTime,
    };
  }

  private convertRegComprasRawValueToRegCompras(
    rawRegCompras: RegComprasFormRawValue | NewRegComprasFormRawValue
  ): IRegCompras | NewRegCompras {
    return {
      ...rawRegCompras,
      fecEmiComp: dayjs(rawRegCompras.fecEmiComp, DATE_TIME_FORMAT),
      fecVencComp: dayjs(rawRegCompras.fecVencComp, DATE_TIME_FORMAT),
      fecEmiModif: dayjs(rawRegCompras.fecEmiModif, DATE_TIME_FORMAT),
      fecEmiDetracc: dayjs(rawRegCompras.fecEmiDetracc, DATE_TIME_FORMAT),
      fecCrea: dayjs(rawRegCompras.fecCrea, DATE_TIME_FORMAT),
      fecModif: dayjs(rawRegCompras.fecModif, DATE_TIME_FORMAT),
    };
  }

  private convertRegComprasToRegComprasRawValue(
    regCompras: IRegCompras | (Partial<NewRegCompras> & RegComprasFormDefaults)
  ): RegComprasFormRawValue | PartialWithRequiredKeyOf<NewRegComprasFormRawValue> {
    return {
      ...regCompras,
      fecEmiComp: regCompras.fecEmiComp ? regCompras.fecEmiComp.format(DATE_TIME_FORMAT) : undefined,
      fecVencComp: regCompras.fecVencComp ? regCompras.fecVencComp.format(DATE_TIME_FORMAT) : undefined,
      fecEmiModif: regCompras.fecEmiModif ? regCompras.fecEmiModif.format(DATE_TIME_FORMAT) : undefined,
      fecEmiDetracc: regCompras.fecEmiDetracc ? regCompras.fecEmiDetracc.format(DATE_TIME_FORMAT) : undefined,
      fecCrea: regCompras.fecCrea ? regCompras.fecCrea.format(DATE_TIME_FORMAT) : undefined,
      fecModif: regCompras.fecModif ? regCompras.fecModif.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
