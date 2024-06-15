import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IPrecuenta } from '../precuenta.model';
import { PrecuentaService } from '../service/precuenta.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './precuenta-delete-dialog.component.html',
})
export class PrecuentaDeleteDialogComponent {
  precuenta?: IPrecuenta;

  constructor(protected precuentaService: PrecuentaService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.precuentaService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
