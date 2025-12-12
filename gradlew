#!/usr/bin/env sh
set -e
DIR="$(cd "$(dirname "$0")" && pwd)"

# желаемая версия и имя архива
GRADLE_VER="8.7"
ARCHIVE="gradle-$GRADLE_VER-bin.zip"
GRADLE_DIR="$DIR/gradle"

# если gradle/bin/gradle существует — просто запускаем
if [ -x "$GRADLE_DIR/bin/gradle" ]; then
  exec "$GRADLE_DIR/bin/gradle" "$@"
fi

# если нет — пытаемся распаковать локальный архив (в корне проекта)
if [ -f "$DIR/$ARCHIVE" ]; then
  echo "Found local $ARCHIVE, unpacking to $GRADLE_DIR ..."
  rm -rf "$GRADLE_DIR"
  unzip -q "$DIR/$ARCHIVE"
  mv "$DIR/gradle-$GRADLE_VER" "$GRADLE_DIR"
  chmod +x "$GRADLE_DIR/bin/gradle"
  exec "$GRADLE_DIR/bin/gradle" "$@"
fi

# иначе — скачиваем официально, распаковываем
echo "Downloading $ARCHIVE ..."
wget -q "https://services.gradle.org/distributions/$ARCHIVE" -O "$DIR/$ARCHIVE"
rm -rf "$GRADLE_DIR"
unzip -q "$DIR/$ARCHIVE"
mv "$DIR/gradle-$GRADLE_VER" "$GRADLE_DIR"
chmod +x "$GRADLE_DIR/bin/gradle"

exec "$GRADLE_DIR/bin/gradle" "$@"
