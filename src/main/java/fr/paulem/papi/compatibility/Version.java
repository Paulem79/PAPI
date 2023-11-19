package fr.paulem.papi.compatibility;

public class Version {
    private final int major, minor, revision;

    public Version(int major, int minor, int revision){
        this.major = major;
        this.minor = minor;
        this.revision = revision;
    }

    /**
     * 1.19.4 -> 1
     *
     * @return the major
     */
    public int getMajor() {
        return major;
    }

    /**
     * 1.19.4 -> 19
     *
     * @return the minor
     */
    public int getMinor() {
        return minor;
    }

    /**
     * 1.19.4 -> 4
     *
     * @return the revision
     */
    public int getRevision() {
        return revision;
    }

    public static Version getVersion(VersionMethod versionMethod) {
        int major, minor, revision;

        if (versionMethod == VersionMethod.BUKKIT) {
            String version = VersionMethod.BUKKIT.getVersion();
            String[] parts = version.split("-")[0].split("\\.");

            major = Integer.parseInt(parts[0]);
            minor = Integer.parseInt(parts[1]);
            try {
                revision = Integer.parseInt(parts[2]);
            } catch (NumberFormatException err) {
                revision = 0;
            }
        } else if (versionMethod == VersionMethod.SERVER) {
            String version = VersionMethod.SERVER.getVersion();

            // Extraire la version de Minecraft
            String mcVersion = version.substring(version.indexOf("MC: ") + 4, version.length() - 1);
            String[] mcParts = mcVersion.split("\\.");

            major = Integer.parseInt(mcParts[0]);
            minor = Integer.parseInt(mcParts[1]);
            revision = Integer.parseInt(mcParts[2]);
        } else throw new IllegalArgumentException("Invalid VersionMethod enum value");
        return new Version(major, minor, revision);
    }

    @Override
    public String toString() {
        return getMajor() + "." + getMinor() + "." + getRevision();
    }
}
