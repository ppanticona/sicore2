import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ISecuencias } from '../secuencias.model';
import { SecuenciasService } from '../service/secuencias.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './secuencias-delete-dialog.component.html',
})
export class SecuenciasDeleteDialogComponent {
  secuencias?: ISecuencias;

  constructor(protected secuenciasService: SecuenciasService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.secuenciasService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
