import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IAutorizacion } from '../autorizacion.model';
import { AutorizacionService } from '../service/autorizacion.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './autorizacion-delete-dialog.component.html',
})
export class AutorizacionDeleteDialogComponent {
  autorizacion?: IAutorizacion;

  constructor(protected autorizacionService: AutorizacionService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.autorizacionService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
