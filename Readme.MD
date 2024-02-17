# [AutoUpdateIP](https://github.com/ctih1/AutoUpdateIP)
## Automatically updates your Cloudflare domain's IP.

# Installation

1. Drag the .jar file into /plugins/
2. Fill in the fields in plugins/AutoUpdateIP/config.yml
3. Start the server.

# Fields
- ZoneID
    - [Find zone and account IDs · Cloudflare Fundamentals docs](https://developers.cloudflare.com/fundamentals/setup/find-account-and-zone-ids/#:~:text=Log%20in%20to%20the%20Cloudflare%20dashboard.%20Select%20your,commands%20or%20other%20tasks%2C%20select%20Click%20to%20copy.)
- DnsRecordID
    - [List DNS Records](https://developers.cloudflare.com/api/operations/dns-records-for-a-zone-list-dns-records)
- Email
    - The email that your accounts are linked to.
- AuthKey
    - [User API Tokens](https://dash.cloudflare.com/profile/api-tokens)
    1. Api Tokens
    2. Create Token
    3. Create Custom Token
    - Permissions
        - Zone | DNS | Edit
    - Zone Resources
        - Include | Specific zone | your domain
    - Client IP Address Filtering (optional)
    - TTL
        - Today -> Any date you want.
- URL
    - Your URL