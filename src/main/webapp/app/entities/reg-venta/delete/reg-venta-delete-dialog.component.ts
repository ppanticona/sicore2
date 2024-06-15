import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IRegVenta } from '../reg-venta.model';
import { RegVentaService } from '../service/reg-venta.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './reg-venta-delete-dialog.component.html',
})
export class RegVentaDeleteDialogComponent {
  regVenta?: IRegVenta;

  constructor(protected regVentaService: RegVentaService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.regVentaService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
