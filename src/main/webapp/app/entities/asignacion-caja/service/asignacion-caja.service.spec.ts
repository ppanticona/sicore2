import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IAsignacionCaja } from '../asignacion-caja.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../asignacion-caja.test-samples';

import { AsignacionCajaService, RestAsignacionCaja } from './asignacion-caja.service';

const requireRestSample: RestAsignacionCaja = {
  ...sampleWithRequiredData,
  fecAsignacion: sampleWithRequiredData.fecAsignacion?.toJSON(),
  fecCrea: sampleWithRequiredData.fecCrea?.toJSON(),
  fecModif: sampleWithRequiredData.fecModif?.toJSON(),
};

describe('AsignacionCaja Service', () => {
  let service: AsignacionCajaService;
  let httpMock: HttpTestingController;
  let expectedResult: IAsignacionCaja | IAsignacionCaja[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(AsignacionCajaService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.find('ABC').subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should create a AsignacionCaja', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const asignacionCaja = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(asignacionCaja).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a AsignacionCaja', () => {
      const asignacionCaja = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(asignacionCaja).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a AsignacionCaja', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of AsignacionCaja', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a AsignacionCaja', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addAsignacionCajaToCollectionIfMissing', () => {
      it('should add a AsignacionCaja to an empty array', () => {
        const asignacionCaja: IAsignacionCaja = sampleWithRequiredData;
        expectedResult = service.addAsignacionCajaToCollectionIfMissing([], asignacionCaja);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(asignacionCaja);
      });

      it('should not add a AsignacionCaja to an array that contains it', () => {
        const asignacionCaja: IAsignacionCaja = sampleWithRequiredData;
        const asignacionCajaCollection: IAsignacionCaja[] = [
          {
            ...asignacionCaja,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addAsignacionCajaToCollectionIfMissing(asignacionCajaCollection, asignacionCaja);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a AsignacionCaja to an array that doesn't contain it", () => {
        const asignacionCaja: IAsignacionCaja = sampleWithRequiredData;
        const asignacionCajaCollection: IAsignacionCaja[] = [sampleWithPartialData];
        expectedResult = service.addAsignacionCajaToCollectionIfMissing(asignacionCajaCollection, asignacionCaja);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(asignacionCaja);
      });

      it('should add only unique AsignacionCaja to an array', () => {
        const asignacionCajaArray: IAsignacionCaja[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const asignacionCajaCollection: IAsignacionCaja[] = [sampleWithRequiredData];
        expectedResult = service.addAsignacionCajaToCollectionIfMissing(asignacionCajaCollection, ...asignacionCajaArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const asignacionCaja: IAsignacionCaja = sampleWithRequiredData;
        const asignacionCaja2: IAsignacionCaja = sampleWithPartialData;
        expectedResult = service.addAsignacionCajaToCollectionIfMissing([], asignacionCaja, asignacionCaja2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(asignacionCaja);
        expect(expectedResult).toContain(asignacionCaja2);
      });

      it('should accept null and undefined values', () => {
        const asignacionCaja: IAsignacionCaja = sampleWithRequiredData;
        expectedResult = service.addAsignacionCajaToCollectionIfMissing([], null, asignacionCaja, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(asignacionCaja);
      });

      it('should return initial array if no AsignacionCaja is added', () => {
        const asignacionCajaCollection: IAsignacionCaja[] = [sampleWithRequiredData];
        expectedResult = service.addAsignacionCajaToCollectionIfMissing(asignacionCajaCollection, undefined, null);
        expect(expectedResult).toEqual(asignacionCajaCollection);
      });
    });

    describe('compareAsignacionCaja', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareAsignacionCaja(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareAsignacionCaja(entity1, entity2);
        const compareResult2 = service.compareAsignacionCaja(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareAsignacionCaja(entity1, entity2);
        const compareResult2 = service.compareAsignacionCaja(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareAsignacionCaja(entity1, entity2);
        const compareResult2 = service.compareAsignacionCaja(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
