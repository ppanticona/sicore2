import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IPrecuenta } from '../precuenta.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../precuenta.test-samples';

import { PrecuentaService, RestPrecuenta } from './precuenta.service';

const requireRestSample: RestPrecuenta = {
  ...sampleWithRequiredData,
  fecCrea: sampleWithRequiredData.fecCrea?.toJSON(),
  fecModif: sampleWithRequiredData.fecModif?.toJSON(),
};

describe('Precuenta Service', () => {
  let service: PrecuentaService;
  let httpMock: HttpTestingController;
  let expectedResult: IPrecuenta | IPrecuenta[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(PrecuentaService);
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

    it('should create a Precuenta', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const precuenta = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(precuenta).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Precuenta', () => {
      const precuenta = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(precuenta).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Precuenta', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Precuenta', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Precuenta', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addPrecuentaToCollectionIfMissing', () => {
      it('should add a Precuenta to an empty array', () => {
        const precuenta: IPrecuenta = sampleWithRequiredData;
        expectedResult = service.addPrecuentaToCollectionIfMissing([], precuenta);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(precuenta);
      });

      it('should not add a Precuenta to an array that contains it', () => {
        const precuenta: IPrecuenta = sampleWithRequiredData;
        const precuentaCollection: IPrecuenta[] = [
          {
            ...precuenta,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addPrecuentaToCollectionIfMissing(precuentaCollection, precuenta);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Precuenta to an array that doesn't contain it", () => {
        const precuenta: IPrecuenta = sampleWithRequiredData;
        const precuentaCollection: IPrecuenta[] = [sampleWithPartialData];
        expectedResult = service.addPrecuentaToCollectionIfMissing(precuentaCollection, precuenta);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(precuenta);
      });

      it('should add only unique Precuenta to an array', () => {
        const precuentaArray: IPrecuenta[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const precuentaCollection: IPrecuenta[] = [sampleWithRequiredData];
        expectedResult = service.addPrecuentaToCollectionIfMissing(precuentaCollection, ...precuentaArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const precuenta: IPrecuenta = sampleWithRequiredData;
        const precuenta2: IPrecuenta = sampleWithPartialData;
        expectedResult = service.addPrecuentaToCollectionIfMissing([], precuenta, precuenta2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(precuenta);
        expect(expectedResult).toContain(precuenta2);
      });

      it('should accept null and undefined values', () => {
        const precuenta: IPrecuenta = sampleWithRequiredData;
        expectedResult = service.addPrecuentaToCollectionIfMissing([], null, precuenta, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(precuenta);
      });

      it('should return initial array if no Precuenta is added', () => {
        const precuentaCollection: IPrecuenta[] = [sampleWithRequiredData];
        expectedResult = service.addPrecuentaToCollectionIfMissing(precuentaCollection, undefined, null);
        expect(expectedResult).toEqual(precuentaCollection);
      });
    });

    describe('comparePrecuenta', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.comparePrecuenta(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.comparePrecuenta(entity1, entity2);
        const compareResult2 = service.comparePrecuenta(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.comparePrecuenta(entity1, entity2);
        const compareResult2 = service.comparePrecuenta(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.comparePrecuenta(entity1, entity2);
        const compareResult2 = service.comparePrecuenta(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
