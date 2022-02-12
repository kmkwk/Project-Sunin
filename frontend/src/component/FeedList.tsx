import Link from "next/link";
import { Card, Grid, Icon, Image } from "semantic-ui-react";

export default function FeedList({ list }: any) {
  function count(list: any) {
    if (!list || list.length == 0) return 0;
    return list.length;
  }

  return (
    <>
      <Grid columns={3} stackable container>
        <Grid.Row>
          {list &&
            list.map((item: any) => (
              <Grid.Column key={item.id}>
                <Link href={`/feed/personal/${item.id}`}>
                  <Card>
                    <Image src={item.filePath[0]} alt={item.filePath[0]} />
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
                        <span>{item.modifiedDate}</span>
                      </Card.Meta>
                      <Card.Description>{item.content}</Card.Description>
                    </Card.Content>
                    <Card.Content extra textAlign="center">
                      <span>
                        {item.likes}&nbsp;&nbsp;
                        <Icon name="like" color="red" />
                      </span>
                      <span> </span>
                      <span>
                        {count(item.comments)}&nbsp;&nbsp;
                        <Icon name="comments" color="blue" />
                      </span>
                    </Card.Content>
                  </Card>
                </Link>
              </Grid.Column>
            ))}
        </Grid.Row>
      </Grid>
    </>
  );
}
