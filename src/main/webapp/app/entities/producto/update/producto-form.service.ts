import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IProducto, NewProducto } from '../producto.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IProducto for edit and NewProductoFormGroupInput for create.
 */
type ProductoFormGroupInput = IProducto | PartialWithRequiredKeyOf<NewProducto>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IProducto | NewProducto> = Omit<T, 'fecIniVig' | 'fecFinVig' | 'fecCrea' | 'fecModif'> & {
  fecIniVig?: string | null;
  fecFinVig?: string | null;
  fecCrea?: string | null;
  fecModif?: string | null;
};

type ProductoFormRawValue = FormValueOf<IProducto>;

type NewProductoFormRawValue = FormValueOf<NewProducto>;

type ProductoFormDefaults = Pick<NewProducto, 'id' | 'fecIniVig' | 'fecFinVig' | 'indDel' | 'fecCrea' | 'fecModif'>;

type ProductoFormGroupContent = {
  id: FormControl<ProductoFormRawValue['id'] | NewProducto['id']>;
  codProducto: FormControl<ProductoFormRawValue['codProducto']>;
  tipProducto: FormControl<ProductoFormRawValue['tipProducto']>;
  codQr: FormControl<ProductoFormRawValue['codQr']>;
  codBarra: FormControl<ProductoFormRawValue['codBarra']>;
  descripcion: FormControl<ProductoFormRawValue['descripcion']>;
  detalleDesc: FormControl<ProductoFormRawValue['detalleDesc']>;
  valor: FormControl<ProductoFormRawValue['valor']>;
  categoria: FormControl<ProductoFormRawValue['categoria']>;
  subCategoria: FormControl<ProductoFormRawValue['subCategoria']>;
  categoriaMenu: FormControl<ProductoFormRawValue['categoriaMenu']>;
  urlImage: FormControl<ProductoFormRawValue['urlImage']>;
  fecIniVig: FormControl<ProductoFormRawValue['fecIniVig']>;
  fecFinVig: FormControl<ProductoFormRawValue['fecFinVig']>;
  costoProd: FormControl<ProductoFormRawValue['costoProd']>;
  estado: FormControl<ProductoFormRawValue['estado']>;
  version: FormControl<ProductoFormRawValue['version']>;
  indDel: FormControl<ProductoFormRawValue['indDel']>;
  fecCrea: FormControl<ProductoFormRawValue['fecCrea']>;
  usuCrea: FormControl<ProductoFormRawValue['usuCrea']>;
  ipCrea: FormControl<ProductoFormRawValue['ipCrea']>;
  fecModif: FormControl<ProductoFormRawValue['fecModif']>;
  usuModif: FormControl<ProductoFormRawValue['usuModif']>;
  ipModif: FormControl<ProductoFormRawValue['ipModif']>;
};

export type ProductoFormGroup = FormGroup<ProductoFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class ProductoFormService {
  createProductoFormGroup(producto: ProductoFormGroupInput = { id: null }): ProductoFormGroup {
    const productoRawValue = this.convertProductoToProductoRawValue({
      ...this.getFormDefaults(),
      ...producto,
    });
    return new FormGroup<ProductoFormGroupContent>({
      id: new FormControl(
        { value: productoRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      codProducto: new FormControl(productoRawValue.codProducto),
      tipProducto: new FormControl(productoRawValue.tipProducto),
      codQr: new FormControl(productoRawValue.codQr),
      codBarra: new FormControl(productoRawValue.codBarra),
      descripcion: new FormControl(productoRawValue.descripcion, {
        validators: [Validators.required],
      }),
      detalleDesc: new FormControl(productoRawValue.detalleDesc),
      valor: new FormControl(productoRawValue.valor),
      categoria: new FormControl(productoRawValue.categoria),
      subCategoria: new FormControl(productoRawValue.subCategoria),
      categoriaMenu: new FormControl(productoRawValue.categoriaMenu),
      urlImage: new FormControl(productoRawValue.urlImage),
      fecIniVig: new FormControl(productoRawValue.fecIniVig),
      fecFinVig: new FormControl(productoRawValue.fecFinVig),
      costoProd: new FormControl(productoRawValue.costoProd),
      estado: new FormControl(productoRawValue.estado, {
        validators: [Validators.required],
      }),
      version: new FormControl(productoRawValue.version, {
        validators: [Validators.required],
      }),
      indDel: new FormControl(productoRawValue.indDel, {
        validators: [Validators.required],
      }),
      fecCrea: new FormControl(productoRawValue.fecCrea, {
        validators: [Validators.required],
      }),
      usuCrea: new FormControl(productoRawValue.usuCrea, {
        validators: [Validators.required],
      }),
      ipCrea: new FormControl(productoRawValue.ipCrea, {
        validators: [Validators.required],
      }),
      fecModif: new FormControl(productoRawValue.fecModif),
      usuModif: new FormControl(productoRawValue.usuModif),
      ipModif: new FormControl(productoRawValue.ipModif),
    });
  }

  getProducto(form: ProductoFormGroup): IProducto | NewProducto {
    return this.convertProductoRawValueToProducto(form.getRawValue() as ProductoFormRawValue | NewProductoFormRawValue);
  }

  resetForm(form: ProductoFormGroup, producto: ProductoFormGroupInput): void {
    const productoRawValue = this.convertProductoToProductoRawValue({ ...this.getFormDefaults(), ...producto });
    form.reset(
      {
        ...productoRawValue,
        id: { value: productoRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): ProductoFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      fecIniVig: currentTime,
      fecFinVig: currentTime,
      indDel: false,
      fecCrea: currentTime,
      fecModif: currentTime,
    };
  }

  private convertProductoRawValueToProducto(rawProducto: ProductoFormRawValue | NewProductoFormRawValue): IProducto | NewProducto {
    return {
      ...rawProducto,
      fecIniVig: dayjs(rawProducto.fecIniVig, DATE_TIME_FORMAT),
      fecFinVig: dayjs(rawProducto.fecFinVig, DATE_TIME_FORMAT),
      fecCrea: dayjs(rawProducto.fecCrea, DATE_TIME_FORMAT),
      fecModif: dayjs(rawProducto.fecModif, DATE_TIME_FORMAT),
    };
  }

  private convertProductoToProductoRawValue(
    producto: IProducto | (Partial<NewProducto> & ProductoFormDefaults)
  ): ProductoFormRawValue | PartialWithRequiredKeyOf<NewProductoFormRawValue> {
    return {
      ...producto,
      fecIniVig: producto.fecIniVig ? producto.fecIniVig.format(DATE_TIME_FORMAT) : undefined,
      fecFinVig: producto.fecFinVig ? producto.fecFinVig.format(DATE_TIME_FORMAT) : undefined,
      fecCrea: producto.fecCrea ? producto.fecCrea.format(DATE_TIME_FORMAT) : undefined,
      fecModif: producto.fecModif ? producto.fecModif.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
