
# EulerNumber Class Documentation

## Overview

The `EulerNumber` class calculates and represents the value of the mathematical constant 'e' to a specified number
of decimal places using an iterative algorithm. The value of 'e' is computed using the series expansion:

$$
e = \frac{1}{0!} + \frac{1}{1!} + \frac{1}{2!} + \frac{1}{3!} + \ldots
$$

## Constructor

### `EulerNumber(int decimalPlaces)`

- **Description**: Initializes the `EulerNumber` class and calculates the value of 'e' to the specified number of decimal places.
- **Parameters**:
    - `decimalPlaces`: The number of decimal places to compute. Must be greater than 0.
- **Throws**:
    - `IllegalArgumentException`: If `decimalPlaces` is less than or equal to 0.

## Methods

### `private void calculate()`

- **Description**: Calculates the value of 'e' using an iterative algorithm. The value is stored in the `e` array.

### `private boolean nonNullTerm()`

- **Description**: Checks if the current term in the series is non-null (it doesn't contain only nulls).
- **Returns**: `true` if the term is non-null, `false` otherwise.

### `private void divideTerm(int n)`

- **Description**: Divides the current term in the series by the next integer `n` in the series. The result is the next term
in the series and is stored in the `term` array.
- **Parameters**:
    - `n`: The next integer in the series.

### `private void addTerm()`

- **Description**: Adds the current term in the series to the computed value of 'e'.

### `public void round()`

- **Description**: Rounds the computed value of 'e' to the specified number of decimal places.

### `public String toString()`

- **Description**: Returns a string representation of the computed value of 'e' up to the specified number of decimal places.
- **Returns**: A string representing the value of 'e'.

# Control Class Documentation

## Overview

The `Control` class provides methods to read and validate the value of the mathematical constant 'e' from a control file.
It allows caching of the value for efficiency and offers methods to verify the correctness of a computed Euler number
against the control value. Source of the correct value of 'e': https://apod.nasa.gov/htmltest/gifcity/e.1mil

## Fields

- **`controlPathname`**: Path to the control file.
- **`controlFile`**: A `File` object representing the control file.
- **`cachedE`**: A cached string of the control value of 'e'.

## Methods

### `public static String getControlE(int length)`

- **Description**: Retrieves the control value of 'e' up to the specified length. If the requested length is already cached,
it returns the cached value.
- **Parameters**:
    - `length`: The number of characters of 'e' to retrieve (must be > 0 and <= 1 000 022).
- **Returns**: A string containing the first `length` characters of the control value of 'e'.

### `public static void cacheE(int length)`

- **Description**: Caches the control value of 'e' up to the specified length for future use.
- **Parameters**:
    - `length`: The number of characters of 'e' to cache (must be > 0 and <= 1 000 022).

### `public static boolean isCorrect(EulerNumber eulerNumber)`

- **Description**: Compares the computed value of 'e' with the control value to check for correctness.
- **Parameters**:
    - `eulerNumber`: The computed Euler number to validate.
- **Returns**: `true` if the computed value matches the control value, `false` otherwise.

### `public static String getFirstCorrectPart(EulerNumber eulerNumber)`

- **Description**: Retrieves the longest correct initial segment of the computed Euler number that matches the control value.
- **Parameters**:
    - `eulerNumber`: The computed Euler number.
- **Returns**: A string representing the longest correct initial segment of the computed Euler number.