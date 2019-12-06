class PurchaseOrder {
  String address;
  String alasan;
  dynamic biayaKirim;
  String buyerId;
  String buyerName;
  String buyerPhone;
  String cityId;
  String cityName;
  String createUser;
  dynamic createdDate;
  String customerLocation;
  List<DetailPurchase> detail;
  dynamic discount;
  String fileUrl;
  dynamic jumlah;
  String justifikasi;
  String kecamatanId;
  String kecamatanName;
  String kelurahanId;
  String kelurahanName;
  String keterangan;
  List<ListDeliveryOrder> listDeliveryOrder;
  String nopo;
  String picId;
  String picName;
  String picPhone;
  String postalCode;
  dynamic ppn;
  String provinceId;
  String provinceName;
  dynamic ratingDeliveryTime;
  dynamic ratingQuality;
  dynamic ratingService;
  String rejectorCompanyId;
  String rejectorLevel;
  dynamic rop;
  String status;
  dynamic stokUnit;
  dynamic subTotal;
  String supplierId;
  bool supplierIsdynamicernalProvider;
  String supplierLocation;
  String supplierName;
  String supplierPhone;
  dynamic total;
  dynamic totalRating;
  String unitId;
  String unitName;

  PurchaseOrder(
      {this.address,
        this.alasan,
        this.biayaKirim,
        this.buyerId,
        this.buyerName,
        this.buyerPhone,
        this.cityId,
        this.cityName,
        this.createUser,
        this.createdDate,
        this.customerLocation,
        this.detail,
        this.discount,
        this.fileUrl,
        this.jumlah,
        this.justifikasi,
        this.kecamatanId,
        this.kecamatanName,
        this.kelurahanId,
        this.kelurahanName,
        this.keterangan,
        this.listDeliveryOrder,
        this.nopo,
        this.picId,
        this.picName,
        this.picPhone,
        this.postalCode,
        this.ppn,
        this.provinceId,
        this.provinceName,
        this.ratingDeliveryTime,
        this.ratingQuality,
        this.ratingService,
        this.rejectorCompanyId,
        this.rejectorLevel,
        this.rop,
        this.status,
        this.stokUnit,
        this.subTotal,
        this.supplierId,
        this.supplierIsdynamicernalProvider,
        this.supplierLocation,
        this.supplierName,
        this.supplierPhone,
        this.total,
        this.totalRating,
        this.unitId,
        this.unitName});

  PurchaseOrder.fromJson(Map<String, dynamic> json) {
    address = json['address'];
    alasan = json['alasan'];
    biayaKirim = json['biayaKirim'];
    buyerId = json['buyerId'];
    buyerName = json['buyerName'];
    buyerPhone = json['buyerPhone'];
    cityId = json['cityId'];
    cityName = json['cityName'];
    createUser = json['createUser'];
    createdDate = json['createdDate'];
    customerLocation = json['customerLocation'];
    if (json['detail'] != null) {
      detail = new List<DetailPurchase>();
      json['detail'].forEach((v) {
        detail.add(new DetailPurchase.fromJson(v));
      });
    }
    discount = json['discount'];
    fileUrl = json['fileUrl'];
    jumlah = json['jumlah'];
    justifikasi = json['justifikasi'];
    kecamatanId = json['kecamatanId'];
    kecamatanName = json['kecamatanName'];
    kelurahanId = json['kelurahanId'];
    kelurahanName = json['kelurahanName'];
    keterangan = json['keterangan'];
    if (json['listDeliveryOrder'] != null) {
      listDeliveryOrder = new List<ListDeliveryOrder>();
      json['listDeliveryOrder'].forEach((v) {
        listDeliveryOrder.add(new ListDeliveryOrder.fromJson(v));
      });
    }
    nopo = json['nopo'];
    picId = json['picId'];
    picName = json['picName'];
    picPhone = json['picPhone'];
    postalCode = json['postalCode'];
    ppn = json['ppn'];
    provinceId = json['provinceId'];
    provinceName = json['provinceName'];
    ratingDeliveryTime = json['ratingDeliveryTime'];
    ratingQuality = json['ratingQuality'];
    ratingService = json['ratingService'];
    rejectorCompanyId = json['rejectorCompanyId'];
    rejectorLevel = json['rejectorLevel'];
    rop = json['rop'];
    status = json['status'];
    stokUnit = json['stokUnit'];
    subTotal = json['subTotal'];
    supplierId = json['supplierId'];
    supplierIsdynamicernalProvider = json['supplierIsdynamicernalProvider'];
    supplierLocation = json['supplierLocation'];
    supplierName = json['supplierName'];
    supplierPhone = json['supplierPhone'];
    total = json['total'];
    totalRating = json['totalRating'];
    unitId = json['unitId'];
    unitName = json['unitName'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['address'] = this.address;
    data['alasan'] = this.alasan;
    data['biayaKirim'] = this.biayaKirim;
    data['buyerId'] = this.buyerId;
    data['buyerName'] = this.buyerName;
    data['buyerPhone'] = this.buyerPhone;
    data['cityId'] = this.cityId;
    data['cityName'] = this.cityName;
    data['createUser'] = this.createUser;
    data['createdDate'] = this.createdDate;
    data['customerLocation'] = this.customerLocation;
    if (this.detail != null) {
      data['detail'] = this.detail.map((v) => v.toJson()).toList();
    }
    data['discount'] = this.discount;
    data['fileUrl'] = this.fileUrl;
    data['jumlah'] = this.jumlah;
    data['justifikasi'] = this.justifikasi;
    data['kecamatanId'] = this.kecamatanId;
    data['kecamatanName'] = this.kecamatanName;
    data['kelurahanId'] = this.kelurahanId;
    data['kelurahanName'] = this.kelurahanName;
    data['keterangan'] = this.keterangan;
    if (this.listDeliveryOrder != null) {
      data['listDeliveryOrder'] =
          this.listDeliveryOrder.map((v) => v.toJson()).toList();
    }
    data['nopo'] = this.nopo;
    data['picId'] = this.picId;
    data['picName'] = this.picName;
    data['picPhone'] = this.picPhone;
    data['postalCode'] = this.postalCode;
    data['ppn'] = this.ppn;
    data['provinceId'] = this.provinceId;
    data['provinceName'] = this.provinceName;
    data['ratingDeliveryTime'] = this.ratingDeliveryTime;
    data['ratingQuality'] = this.ratingQuality;
    data['ratingService'] = this.ratingService;
    data['rejectorCompanyId'] = this.rejectorCompanyId;
    data['rejectorLevel'] = this.rejectorLevel;
    data['rop'] = this.rop;
    data['status'] = this.status;
    data['stokUnit'] = this.stokUnit;
    data['subTotal'] = this.subTotal;
    data['supplierId'] = this.supplierId;
    data['supplierIsdynamicernalProvider'] = this.supplierIsdynamicernalProvider;
    data['supplierLocation'] = this.supplierLocation;
    data['supplierName'] = this.supplierName;
    data['supplierPhone'] = this.supplierPhone;
    data['total'] = this.total;
    data['totalRating'] = this.totalRating;
    data['unitId'] = this.unitId;
    data['unitName'] = this.unitName;
    return data;
  }
}

class DetailPurchase {
  dynamic basePrice;
  dynamic baseQty;
  String categoryId;
  String categoryName;
  String deliveryTime;
  String description;
  dynamic hargaSatuan;
  String itemId;
  String noPOSAP;
  String noregver;
  String productName;
  dynamic qty;
  dynamic qtyDoTerima;
  dynamic qtyDoTerkirim;
  String sku;
  dynamic totalHarga;

  DetailPurchase(
      {this.basePrice,
        this.baseQty,
        this.categoryId,
        this.categoryName,
        this.deliveryTime,
        this.description,
        this.hargaSatuan,
        this.itemId,
        this.noPOSAP,
        this.noregver,
        this.productName,
        this.qty,
        this.qtyDoTerima,
        this.qtyDoTerkirim,
        this.sku,
        this.totalHarga});

  DetailPurchase.fromJson(Map<String, dynamic> json) {
    basePrice = json['basePrice'];
    baseQty = json['baseQty'];
    categoryId = json['categoryId'];
    categoryName = json['categoryName'];
    deliveryTime = json['deliveryTime'];
    description = json['description'];
    hargaSatuan = json['hargaSatuan'];
    itemId = json['itemId'];
    noPOSAP = json['noPOSAP'];
    noregver = json['noregver'];
    productName = json['productName'];
    qty = json['qty'];
    qtyDoTerima = json['qtyDoTerima'];
    qtyDoTerkirim = json['qtyDoTerkirim'];
    sku = json['sku'];
    totalHarga = json['totalHarga'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['basePrice'] = this.basePrice;
    data['baseQty'] = this.baseQty;
    data['categoryId'] = this.categoryId;
    data['categoryName'] = this.categoryName;
    data['deliveryTime'] = this.deliveryTime;
    data['description'] = this.description;
    data['hargaSatuan'] = this.hargaSatuan;
    data['itemId'] = this.itemId;
    data['noPOSAP'] = this.noPOSAP;
    data['noregver'] = this.noregver;
    data['productName'] = this.productName;
    data['qty'] = this.qty;
    data['qtyDoTerima'] = this.qtyDoTerima;
    data['qtyDoTerkirim'] = this.qtyDoTerkirim;
    data['sku'] = this.sku;
    data['totalHarga'] = this.totalHarga;
    return data;
  }
}

class ListDeliveryOrder {
  String address;
  String buyerId;
  String buyerName;
  String buyerPhone;
  String cityId;
  String cityName;
  String createUser;
  dynamic createdDate;
  String customerLocation;
  dynamic deliveryTime;
  List<Detail> detail;
  String driverName;
  String driverPhone;
  dynamic eta;
  dynamic etd;
  dynamic jumlah;
  String kecamatanId;
  String kecamatanName;
  String kelurahanId;
  String kelurahanName;
  String namaEkspedisi;
  String nodo;
  String noekspedisi;
  String nopo;
  String nopolice;
  String postalCode;
  String provinceId;
  String provinceName;
  dynamic rating;
  String remark;
  String status;
  String supplierId;
  bool supplierIsdynamicernalProvider;
  String supplierLocation;
  String supplierName;
  String supplierPhone;
  dynamic tanggalDiterima;
  String unitName;
  dynamic updatedDate;

  ListDeliveryOrder(
      {this.address,
        this.buyerId,
        this.buyerName,
        this.buyerPhone,
        this.cityId,
        this.cityName,
        this.createUser,
        this.createdDate,
        this.customerLocation,
        this.deliveryTime,
        this.detail,
        this.driverName,
        this.driverPhone,
        this.eta,
        this.etd,
        this.jumlah,
        this.kecamatanId,
        this.kecamatanName,
        this.kelurahanId,
        this.kelurahanName,
        this.namaEkspedisi,
        this.nodo,
        this.noekspedisi,
        this.nopo,
        this.nopolice,
        this.postalCode,
        this.provinceId,
        this.provinceName,
        this.rating,
        this.remark,
        this.status,
        this.supplierId,
        this.supplierIsdynamicernalProvider,
        this.supplierLocation,
        this.supplierName,
        this.supplierPhone,
        this.tanggalDiterima,
        this.unitName,
        this.updatedDate});

  ListDeliveryOrder.fromJson(Map<String, dynamic> json) {
    address = json['address'];
    buyerId = json['buyerId'];
    buyerName = json['buyerName'];
    buyerPhone = json['buyerPhone'];
    cityId = json['cityId'];
    cityName = json['cityName'];
    createUser = json['createUser'];
    createdDate = json['createdDate'];
    customerLocation = json['customerLocation'];
    deliveryTime = json['deliveryTime'];
    if (json['detail'] != null) {
      detail = new List<Detail>();
      json['detail'].forEach((v) {
        detail.add(new Detail.fromJson(v));
      });
    }
    driverName = json['driverName'];
    driverPhone = json['driverPhone'];
    eta = json['eta'];
    etd = json['etd'];
    jumlah = json['jumlah'];
    kecamatanId = json['kecamatanId'];
    kecamatanName = json['kecamatanName'];
    kelurahanId = json['kelurahanId'];
    kelurahanName = json['kelurahanName'];
    namaEkspedisi = json['namaEkspedisi'];
    nodo = json['nodo'];
    noekspedisi = json['noekspedisi'];
    nopo = json['nopo'];
    nopolice = json['nopolice'];
    postalCode = json['postalCode'];
    provinceId = json['provinceId'];
    provinceName = json['provinceName'];
    rating = json['rating'];
    remark = json['remark'];
    status = json['status'];
    supplierId = json['supplierId'];
    supplierIsdynamicernalProvider = json['supplierIsdynamicernalProvider'];
    supplierLocation = json['supplierLocation'];
    supplierName = json['supplierName'];
    supplierPhone = json['supplierPhone'];
    tanggalDiterima = json['tanggalDiterima'];
    unitName = json['unitName'];
    updatedDate = json['updatedDate'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['address'] = this.address;
    data['buyerId'] = this.buyerId;
    data['buyerName'] = this.buyerName;
    data['buyerPhone'] = this.buyerPhone;
    data['cityId'] = this.cityId;
    data['cityName'] = this.cityName;
    data['createUser'] = this.createUser;
    data['createdDate'] = this.createdDate;
    data['customerLocation'] = this.customerLocation;
    data['deliveryTime'] = this.deliveryTime;
    if (this.detail != null) {
      data['detail'] = this.detail.map((v) => v.toJson()).toList();
    }
    data['driverName'] = this.driverName;
    data['driverPhone'] = this.driverPhone;
    data['eta'] = this.eta;
    data['etd'] = this.etd;
    data['jumlah'] = this.jumlah;
    data['kecamatanId'] = this.kecamatanId;
    data['kecamatanName'] = this.kecamatanName;
    data['kelurahanId'] = this.kelurahanId;
    data['kelurahanName'] = this.kelurahanName;
    data['namaEkspedisi'] = this.namaEkspedisi;
    data['nodo'] = this.nodo;
    data['noekspedisi'] = this.noekspedisi;
    data['nopo'] = this.nopo;
    data['nopolice'] = this.nopolice;
    data['postalCode'] = this.postalCode;
    data['provinceId'] = this.provinceId;
    data['provinceName'] = this.provinceName;
    data['rating'] = this.rating;
    data['remark'] = this.remark;
    data['status'] = this.status;
    data['supplierId'] = this.supplierId;
    data['supplierIsdynamicernalProvider'] = this.supplierIsdynamicernalProvider;
    data['supplierLocation'] = this.supplierLocation;
    data['supplierName'] = this.supplierName;
    data['supplierPhone'] = this.supplierPhone;
    data['tanggalDiterima'] = this.tanggalDiterima;
    data['unitName'] = this.unitName;
    data['updatedDate'] = this.updatedDate;
    return data;
  }
}

class Detail {
  String categoryId;
  String categoryName;
  String description;
  String itemId;
  String productName;
  dynamic qty;
  dynamic qtyTerima;
  String remark;
  String sku;

  Detail(
      {this.categoryId,
        this.categoryName,
        this.description,
        this.itemId,
        this.productName,
        this.qty,
        this.qtyTerima,
        this.remark,
        this.sku});

  Detail.fromJson(Map<String, dynamic> json) {
    categoryId = json['categoryId'];
    categoryName = json['categoryName'];
    description = json['description'];
    itemId = json['itemId'];
    productName = json['productName'];
    qty = json['qty'];
    qtyTerima = json['qtyTerima'];
    remark = json['remark'];
    sku = json['sku'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['categoryId'] = this.categoryId;
    data['categoryName'] = this.categoryName;
    data['description'] = this.description;
    data['itemId'] = this.itemId;
    data['productName'] = this.productName;
    data['qty'] = this.qty;
    data['qtyTerima'] = this.qtyTerima;
    data['remark'] = this.remark;
    data['sku'] = this.sku;
    return data;
  }
}

class PODashboard {
  List<PurchaseOrder> waiting;
  List<PurchaseOrder> draft;
  List<PurchaseOrder> rejected;
  List<PurchaseOrder> delivered;
  List<PurchaseOrder> approved;
  List<PurchaseOrder> pos;

  PODashboard({this.waiting, this.draft, this.rejected, this.delivered,
    this.approved, this.pos});


}
