import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ICaja } from '../caja.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../caja.test-samples';

import { CajaService, RestCaja } from './caja.service';

const requireRestSample: RestCaja = {
  ...sampleWithRequiredData,
  fecCrea: sampleWithRequiredData.fecCrea?.toJSON(),
  fecModif: sampleWithRequiredData.fecModif?.toJSON(),
};

describe('Caja Service', () => {
  let service: CajaService;
  let httpMock: HttpTestingController;
  let expectedResult: ICaja | ICaja[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(CajaService);
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

    it('should create a Caja', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const caja = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(caja).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Caja', () => {
      const caja = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(caja).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Caja', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Caja', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Caja', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addCajaToCollectionIfMissing', () => {
      it('should add a Caja to an empty array', () => {
        const caja: ICaja = sampleWithRequiredData;
        expectedResult = service.addCajaToCollectionIfMissing([], caja);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(caja);
      });

      it('should not add a Caja to an array that contains it', () => {
        const caja: ICaja = sampleWithRequiredData;
        const cajaCollection: ICaja[] = [
          {
            ...caja,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addCajaToCollectionIfMissing(cajaCollection, caja);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Caja to an array that doesn't contain it", () => {
        const caja: ICaja = sampleWithRequiredData;
        const cajaCollection: ICaja[] = [sampleWithPartialData];
        expectedResult = service.addCajaToCollectionIfMissing(cajaCollection, caja);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(caja);
      });

      it('should add only unique Caja to an array', () => {
        const cajaArray: ICaja[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const cajaCollection: ICaja[] = [sampleWithRequiredData];
        expectedResult = service.addCajaToCollectionIfMissing(cajaCollection, ...cajaArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const caja: ICaja = sampleWithRequiredData;
        const caja2: ICaja = sampleWithPartialData;
        expectedResult = service.addCajaToCollectionIfMissing([], caja, caja2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(caja);
        expect(expectedResult).toContain(caja2);
      });

      it('should accept null and undefined values', () => {
        const caja: ICaja = sampleWithRequiredData;
        expectedResult = service.addCajaToCollectionIfMissing([], null, caja, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(caja);
      });

      it('should return initial array if no Caja is added', () => {
        const cajaCollection: ICaja[] = [sampleWithRequiredData];
        expectedResult = service.addCajaToCollectionIfMissing(cajaCollection, undefined, null);
        expect(expectedResult).toEqual(cajaCollection);
      });
    });

    describe('compareCaja', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareCaja(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareCaja(entity1, entity2);
        const compareResult2 = service.compareCaja(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareCaja(entity1, entity2);
        const compareResult2 = service.compareCaja(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareCaja(entity1, entity2);
        const compareResult2 = service.compareCaja(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
