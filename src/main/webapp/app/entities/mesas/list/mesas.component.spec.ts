import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { MesasService } from '../service/mesas.service';

import { MesasComponent } from './mesas.component';

describe('Mesas Management Component', () => {
  let comp: MesasComponent;
  let fixture: ComponentFixture<MesasComponent>;
  let service: MesasService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule.withRoutes([{ path: 'mesas', component: MesasComponent }]), HttpClientTestingModule],
      declarations: [MesasComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            data: of({
              defaultSort: 'id,asc',
            }),
            queryParamMap: of(
              jest.requireActual('@angular/router').convertToParamMap({
                page: '1',
                size: '1',
                sort: 'id,desc',
              })
            ),
            snapshot: { queryParams: {} },
          },
        },
      ],
    })
      .overrideTemplate(MesasComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(MesasComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(MesasService);

    const headers = new HttpHeaders();
    jest.spyOn(service, 'query').mockReturnValue(
      of(
        new HttpResponse({
          body: [{ id: 'ABC' }],
          headers,
        })
      )
    );
  });

  it('Should call load all on init', () => {
    // WHEN
    comp.ngOnInit();

    // THEN
    expect(service.query).toHaveBeenCalled();
    expect(comp.mesas?.[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
  });

  describe('trackId', () => {
    it('Should forward to mesasService', () => {
      const entity = { id: 'ABC' };
      jest.spyOn(service, 'getMesasIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getMesasIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
