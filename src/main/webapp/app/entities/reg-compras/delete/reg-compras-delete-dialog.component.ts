import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IRegCompras } from '../reg-compras.model';
import { RegComprasService } from '../service/reg-compras.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './reg-compras-delete-dialog.component.html',
})
export class RegComprasDeleteDialogComponent {
  regCompras?: IRegCompras;

  constructor(protected regComprasService: RegComprasService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.regComprasService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
