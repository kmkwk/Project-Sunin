import Link from "next/link";
import { Card, Grid, Icon, Image } from "semantic-ui-react";

export default function FeedList({ list }: any) {
  function count(list: any) {
    if (!list || list.length == 0) return 0;
    return list.length;
  }

  return (
    <>
      <Grid columns={1} stackable>
        <Grid.Row>
          {list &&
            list.map((item: any) => (
              <Grid.Column key={item.id}>
                <br />
                <Link href={`/feed/personal/${item.id}`}>
                  <Card fluid>
                    {item.file_path[0] && (
                      <Image
                        src={
                          item.file_path[0]
                            ? item.file_path[0]
                            : "/images/로고.png"
                        }
                        alt={item.file_path[0]}
                        height="500px"
                      />
                    )}

                    <Card.Content>
                      <Image
                        floated="left"
                        size="mini"
                        src={item.user.image}
                        alt={item.user.image}
                        circular
                      />
                      <Card.Header>{item.user.nickName}</Card.Header>
                      <Card.Meta>
                        <span>
                          {item.modified_date.slice(0, 10)}{" "}
                          {item.modified_date.slice(11, 16)}
                        </span>
                      </Card.Meta>
                      <Card.Description>
                        {item.content.slice(0, 30)}
                      </Card.Description>
                    </Card.Content>
                    <Card.Content extra textAlign="center">
                      <span>
                        {item.likes}&nbsp;&nbsp;
                        <Icon name="like" color="red" />
                      </span>
                      <span> </span>
                      <span>
                        {count(item.comments)}##########
                        <Icon name="comments" color="blue" />
                      </span>
                    </Card.Content>
                    <br />
                  </Card>
                </Link>
              </Grid.Column>
            ))}
        </Grid.Row>
      </Grid>
    </>
  );
}
