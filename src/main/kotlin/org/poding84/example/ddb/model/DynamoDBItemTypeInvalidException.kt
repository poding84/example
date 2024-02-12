package org.poding84.example.ddb.model

class DynamoDBItemTypeInvalidException(
    invalidItemValueName: String
): Exception("Given item value (${invalidItemValueName}) is not existing in the ddb schema")